package ir.pajoohan.mce.service.impl;

import ir.pajoohan.mce.dto.BranchDto;
import ir.pajoohan.mce.dto.BranchMapper;
import ir.pajoohan.mce.entity.Branch;
import ir.pajoohan.mce.repository.BranchRepository;
import ir.pajoohan.mce.repository.PersonRepository;
import ir.pajoohan.mce.repository.StateRepository;
import ir.pajoohan.mce.service.BranchService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BranchServiceImpl implements BranchService {

    BranchRepository branchRepository;
    StateRepository stateRepository;
    PersonRepository personRepository;

    /**
     * Setters
     */
    @Autowired
    public void setBranchRepository(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Autowired
    public void setStateRepository(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * Methods
     */
    @Override
    public List<BranchDto> getAll() {
        List<Branch> branchList = branchRepository.findAll();
        List<BranchDto> branchDtoList = new ArrayList<>();
        for (Branch b : branchList) {
            branchDtoList.add(BranchMapper.INSTANCE.branchToBranchDto(b));
        }
        return branchDtoList;
    }

    @Override
    public BranchDto get(Long id) {
        Branch branch = branchRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return BranchMapper.INSTANCE.branchToBranchDto(branch);
    }

    @Override
    public BranchDto save(BranchDto branchDto) {

        if (!stateRepository.existsById(branchDto.getStateId())) {
            throw new EntityNotFoundException("STATE with ID : '" + branchDto.getStateId() + "' not found.");
        }
        if (!personRepository.existsById(branchDto.getPersonId())) {
            throw new EntityNotFoundException("PERSON with ID : '" + branchDto.getPersonId() + "' not found.");
        }
        if (branchDto.getParentId() != null && !branchRepository.existsById(branchDto.getParentId())) {
            throw new EntityNotFoundException("PARENT with ID : '" + branchDto.getParentId() + "' not found.");
        }

        Branch branch = BranchMapper.INSTANCE.branchDtoToBranch(branchDto);
        branch.setId(null);
        if (branchDto.getParentId() == null) {
            branch.setParent(null);
        }

        Branch branchSaved = branchRepository.save(branch);
        return BranchMapper.INSTANCE.branchToBranchDto(branchSaved);
    }

    @Override
    public BranchDto update(BranchDto branchDto) {

        if (branchDto.getId() != null && branchRepository.existsById(branchDto.getId())) {

            if (!stateRepository.existsById(branchDto.getStateId())) {
                throw new EntityNotFoundException("STATE with ID : '" + branchDto.getStateId() + "' not found.");
            }
            if (!personRepository.existsById(branchDto.getPersonId())) {
                throw new EntityNotFoundException("PERSON with ID : '" + branchDto.getPersonId() + "' not found.");
            }
            if (branchDto.getParentId() != null && !branchRepository.existsById(branchDto.getParentId())) {
                throw new EntityNotFoundException("PARENT with ID : '" + branchDto.getParentId() + "' not found.");
            }

            Branch branch = BranchMapper.INSTANCE.branchDtoToBranch(branchDto);
            if (branchDto.getParentId() == null) {
                branch.setParent(null);
            }

            Branch branchSaved = branchRepository.save(branch);
            return BranchMapper.INSTANCE.branchToBranchDto(branchSaved);
        } else {
            throw new EntityNotFoundException("BRANCH with ID : '" + branchDto.getId() + "' not found.");
        }
    }

    @Override
    public void delete(Long id) {
        Branch branch = branchRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        branchRepository.delete(branch);
    }
}

package ir.pajoohan.mce.service.Impl;

import ir.pajoohan.mce.Dto.BranchDto;
import ir.pajoohan.mce.Dto.BranchMapper;
import ir.pajoohan.mce.confiuration.Messages;
import ir.pajoohan.mce.entity.Branch;
import ir.pajoohan.mce.entity.State;
import ir.pajoohan.mce.repository.BranchRepository;
import ir.pajoohan.mce.repository.StateRepository;
import ir.pajoohan.mce.service.BranchService;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BranchServiceImpl implements BranchService {

    BranchRepository branchRepository;
    StateRepository stateRepository;

    @Autowired
    public void setBranchRepository(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Autowired
    public void setStateRepository(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

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
        Branch branch = branchRepository.findById(id).orElseThrow(
                () -> new NoResultException(Messages.get("ex.noDataFound")));

        return BranchMapper.INSTANCE.branchToBranchDto(branch);
    }

    @Override
    public BranchDto save(BranchDto branchDto) {
        State state = stateRepository.findById(branchDto.getStateId()).get();
        Branch branch = BranchMapper.INSTANCE.branchDtoToBranch(branchDto);
        branch.setState(state);
        Branch branchSaved = branchRepository.save(branch);
        return BranchMapper.INSTANCE.branchToBranchDto(branchSaved);
    }

    @Override
    public BranchDto update(BranchDto branchDto) {
        State state = stateRepository.findById(branchDto.getStateId()).get();
        Branch branch = BranchMapper.INSTANCE.branchDtoToBranch(branchDto);
        branch.setState(state);
        Branch branchSaved = branchRepository.save(branch);
        return BranchMapper.INSTANCE.branchToBranchDto(branchSaved);
    }

    @Override
    public void delete(BranchDto branchDto) {
        Branch branch = BranchMapper.INSTANCE.branchDtoToBranch(branchDto);
        State state = stateRepository.findById(branchDto.getStateId()).get();
        branch.setState(state);
        branchRepository.delete(branch);
    }
}
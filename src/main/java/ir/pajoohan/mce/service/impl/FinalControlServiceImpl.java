package ir.pajoohan.mce.service.impl;

import ir.pajoohan.mce.dto.FinalControlDto;
import ir.pajoohan.mce.dto.FinalControlMapper;
import ir.pajoohan.mce.entity.FinalControl;
import ir.pajoohan.mce.entity.Motorcycle;
import ir.pajoohan.mce.entity.Person;
import ir.pajoohan.mce.entity.Status;
import ir.pajoohan.mce.repository.FinalControlRepository;
import ir.pajoohan.mce.repository.MotorcycleRepository;
import ir.pajoohan.mce.repository.PersonRepository;
import ir.pajoohan.mce.repository.StatusRepository;
import ir.pajoohan.mce.service.FinalControlService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FinalControlServiceImpl implements FinalControlService {

    FinalControlRepository finalControlRepository;
    PersonRepository personRepository;
    StatusRepository statusRepository;
    MotorcycleRepository motorcycleRepository;

    /**
     * Setters
     */
    @Autowired
    public void setFinalControlRepository(FinalControlRepository finalControlRepository) {
        this.finalControlRepository = finalControlRepository;
    }

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Autowired
    public void setStatusRepository(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Autowired
    public void setMotorcycleRepository(MotorcycleRepository motorcycleRepository) {
        this.motorcycleRepository = motorcycleRepository;
    }
    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * Methods
     */
    @Override
    public Page<FinalControlDto> getAll(Integer page, Integer size, String sort) {

        Pageable pageable = PageRequest.of(page, size).withSort(Sort.Direction.ASC, sort);
        Page<FinalControl> finalControlPage = finalControlRepository.findAll(pageable);
        List<FinalControl> finalControlList = finalControlPage.stream().toList();

        List<FinalControlDto> finalControlDtoList = new ArrayList<>();
        for (FinalControl s : finalControlList) {
            finalControlDtoList.add(FinalControlMapper.INSTANCE.finalControlToFinalControlDto(s));
        }
        Page<FinalControlDto> finalControlDtoPage = new PageImpl<>(finalControlDtoList, pageable, finalControlPage.getTotalElements());

        return finalControlDtoPage;
    }

    @Override
    public FinalControlDto get(Long id) {
        FinalControl finalControl = finalControlRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return FinalControlMapper.INSTANCE.finalControlToFinalControlDto(finalControl);
    }

    @Override
    public FinalControlDto save(FinalControlDto finalControlDto) {

        if (finalControlDto.getPersonId() == null || !personRepository.existsById(finalControlDto.getPersonId())) {
            throw new EntityNotFoundException("PERSON with ID : '" + finalControlDto.getPersonId() + "' not found.");
        }
        if (finalControlDto.getStatusId() == null || !statusRepository.existsById(finalControlDto.getStatusId())) {
            throw new EntityNotFoundException("STATUS with ID : '" + finalControlDto.getStatusId() + "' not found.");
        }
        if (finalControlDto.getMotorcycleId() == null || !motorcycleRepository.existsById(finalControlDto.getMotorcycleId())) {
            throw new EntityNotFoundException("MOTORCYCLE with ID : '" + finalControlDto.getMotorcycleId() + "' not found.");
        }

        FinalControl finalControl = FinalControlMapper.INSTANCE.finalControlDtoToFinalControl(finalControlDto);
        finalControl.setId(null);
        FinalControl finalControlSaved = finalControlRepository.save(finalControl);
        return FinalControlMapper.INSTANCE.finalControlToFinalControlDto(finalControlSaved);
    }

    @Override
    public FinalControlDto update(FinalControlDto finalControlDto) {
        Optional<FinalControl> optionalFinalControl = finalControlRepository.findById(finalControlDto.getId());
        if (optionalFinalControl.isEmpty()) {
            throw new EntityNotFoundException("FINAL_CONTROL with ID : '" + finalControlDto.getId() + "' not found.");
        }

        if (finalControlDto.getPersonId() == null) {
            throw new EntityNotFoundException("PERSON ID can't be null");
        }
        if (finalControlDto.getStatusId() == null) {
            throw new EntityNotFoundException("STATUS ID can't be null");
        }
        if (finalControlDto.getMotorcycleId() == null) {
            throw new EntityNotFoundException("MOTORCYCLE ID can't be null");
        }

        FinalControl finalControl = optionalFinalControl.get();
        FinalControlMapper.INSTANCE.updateFinalControlFromDto(finalControlDto, finalControl);

        Optional<Person> optionalPerson = personRepository.findById(finalControlDto.getPersonId());
        if (optionalPerson.isEmpty()) {
            throw new EntityNotFoundException("PERSON with ID : '" + finalControlDto.getPersonId() + "' not found.");
        }
        finalControl.setPerson(optionalPerson.get());

        Optional<Status> optionalStatus = statusRepository.findById(finalControlDto.getStatusId());
        if (optionalStatus.isEmpty()) {
            throw new EntityNotFoundException("STATUS with ID : '" + finalControlDto.getStatusId() + "' not found.");
        }
        finalControl.setStatus(optionalStatus.get());

        Optional<Motorcycle> optionalMotorcycle = motorcycleRepository.findById(finalControlDto.getMotorcycleId());
        if (optionalMotorcycle.isEmpty()) {
            throw new EntityNotFoundException("MOTORCYCLE with ID : '" + finalControlDto.getMotorcycleId() + "' not found.");
        }
        finalControl.setMotorcycle(optionalMotorcycle.get());

        FinalControl finalControlSaved = finalControlRepository.save(finalControl);
        return FinalControlMapper.INSTANCE.finalControlToFinalControlDto(finalControlSaved);
    }

    @Override
    public void delete(Long id) {
        FinalControl finalControl = finalControlRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        finalControlRepository.delete(finalControl);
    }
}

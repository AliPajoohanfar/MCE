package ir.pajoohan.mce.service.impl;

import ir.pajoohan.mce.dto.PreDeliveryControlDto;
import ir.pajoohan.mce.dto.PreDeliveryControlMapper;
import ir.pajoohan.mce.entity.Motorcycle;
import ir.pajoohan.mce.entity.Person;
import ir.pajoohan.mce.entity.PreDeliveryControl;
import ir.pajoohan.mce.entity.Status;
import ir.pajoohan.mce.repository.MotorcycleRepository;
import ir.pajoohan.mce.repository.PersonRepository;
import ir.pajoohan.mce.repository.PreDeliveryControlRepository;
import ir.pajoohan.mce.repository.StatusRepository;
import ir.pajoohan.mce.service.PreDeliveryControlService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class PreDeliveryControlServiceImpl implements PreDeliveryControlService {

    PreDeliveryControlRepository preDeliveryControlRepository;
    PersonRepository personRepository;
    StatusRepository statusRepository;
    MotorcycleRepository motorcycleRepository;

    /**
     * Setters
     */
    @Autowired
    public void setPreDeliveryControlRepository(PreDeliveryControlRepository preDeliveryControlRepository) {
        this.preDeliveryControlRepository = preDeliveryControlRepository;
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
    public List<PreDeliveryControlDto> getAll() {
        List<PreDeliveryControl> preDeliveryControlList = preDeliveryControlRepository.findAll();
        List<PreDeliveryControlDto> preDeliveryControlDtoList = new ArrayList<>();
        for (PreDeliveryControl s : preDeliveryControlList) {
            preDeliveryControlDtoList.add(PreDeliveryControlMapper.INSTANCE.preDeliveryControlToPreDeliveryControlDto(s));
        }
        return preDeliveryControlDtoList;
    }

    @Override
    public PreDeliveryControlDto get(Long id) {
        PreDeliveryControl preDeliveryControl = preDeliveryControlRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return PreDeliveryControlMapper.INSTANCE.preDeliveryControlToPreDeliveryControlDto(preDeliveryControl);
    }

    @Override
    public PreDeliveryControlDto save(PreDeliveryControlDto preDeliveryControlDto) {

        if (preDeliveryControlDto.getPersonId() == null || !personRepository.existsById(preDeliveryControlDto.getPersonId())) {
            throw new EntityNotFoundException("PERSON with ID : '" + preDeliveryControlDto.getPersonId() + "' not found.");
        }
        if (preDeliveryControlDto.getStatusId() == null || !statusRepository.existsById(preDeliveryControlDto.getStatusId())) {
            throw new EntityNotFoundException("STATUS with ID : '" + preDeliveryControlDto.getStatusId() + "' not found.");
        }
        if (preDeliveryControlDto.getMotorcycleId() == null || !motorcycleRepository.existsById(preDeliveryControlDto.getMotorcycleId())) {
            throw new EntityNotFoundException("MOTORCYCLE with ID : '" + preDeliveryControlDto.getMotorcycleId() + "' not found.");
        }

        PreDeliveryControl preDeliveryControl = PreDeliveryControlMapper.INSTANCE.preDeliveryControlDtoToPreDeliveryControl(preDeliveryControlDto);
        preDeliveryControl.setId(null);
        PreDeliveryControl preDeliveryControlSaved = preDeliveryControlRepository.save(preDeliveryControl);
        return PreDeliveryControlMapper.INSTANCE.preDeliveryControlToPreDeliveryControlDto(preDeliveryControlSaved);
    }

    @Override
    public PreDeliveryControlDto update(PreDeliveryControlDto preDeliveryControlDto) {
        Optional<PreDeliveryControl> optionalPreDeliveryControl = preDeliveryControlRepository.findById(preDeliveryControlDto.getId());
        if (optionalPreDeliveryControl.isEmpty()) {
            throw new EntityNotFoundException("PRE_DELIVERY_CONTROL with ID : '" + preDeliveryControlDto.getId() + "' not found.");
        }

        if (preDeliveryControlDto.getPersonId() == null) {
            throw new EntityNotFoundException("PERSON ID can't be null");
        }
        if (preDeliveryControlDto.getStatusId() == null) {
            throw new EntityNotFoundException("STATUS ID can't be null");
        }
        if (preDeliveryControlDto.getMotorcycleId() == null) {
            throw new EntityNotFoundException("MOTORCYCLE ID can't be null");
        }

        PreDeliveryControl preDeliveryControl = optionalPreDeliveryControl.get();
        PreDeliveryControlMapper.INSTANCE.updatePreDeliveryControlFromDto(preDeliveryControlDto, preDeliveryControl);

        Optional<Person> optionalPerson = personRepository.findById(preDeliveryControlDto.getPersonId());
        if (optionalPerson.isEmpty()) {
            throw new EntityNotFoundException("PERSON with ID : '" + preDeliveryControlDto.getPersonId() + "' not found.");
        }
        preDeliveryControl.setPerson(optionalPerson.get());

        Optional<Status> optionalStatus = statusRepository.findById(preDeliveryControlDto.getStatusId());
        if (optionalStatus.isEmpty()) {
            throw new EntityNotFoundException("STATUS with ID : '" + preDeliveryControlDto.getStatusId() + "' not found.");
        }
        preDeliveryControl.setStatus(optionalStatus.get());

        Optional<Motorcycle> optionalMotorcycle = motorcycleRepository.findById(preDeliveryControlDto.getMotorcycleId());
        if (optionalMotorcycle.isEmpty()) {
            throw new EntityNotFoundException("MOTORCYCLE with ID : '" + preDeliveryControlDto.getMotorcycleId() + "' not found.");
        }
        preDeliveryControl.setMotorcycle(optionalMotorcycle.get());

        PreDeliveryControl preDeliveryControlSaved = preDeliveryControlRepository.save(preDeliveryControl);
        return PreDeliveryControlMapper.INSTANCE.preDeliveryControlToPreDeliveryControlDto(preDeliveryControlSaved);
    }

    @Override
    public void delete(Long id) {
        PreDeliveryControl preDeliveryControl = preDeliveryControlRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        preDeliveryControlRepository.delete(preDeliveryControl);
    }
}

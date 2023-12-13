package ir.pajoohan.mce.service.impl;

import ir.pajoohan.mce.dto.QualityControlDto;
import ir.pajoohan.mce.dto.QualityControlMapper;
import ir.pajoohan.mce.entity.Motorcycle;
import ir.pajoohan.mce.entity.Person;
import ir.pajoohan.mce.entity.QualityControl;
import ir.pajoohan.mce.entity.Status;
import ir.pajoohan.mce.repository.MotorcycleRepository;
import ir.pajoohan.mce.repository.PersonRepository;
import ir.pajoohan.mce.repository.QualityControlRepository;
import ir.pajoohan.mce.repository.StatusRepository;
import ir.pajoohan.mce.service.QualityControlService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class QualityControlServiceImpl implements QualityControlService {

    QualityControlRepository qualityControlRepository;
    PersonRepository personRepository;
    StatusRepository statusRepository;
    MotorcycleRepository motorcycleRepository;

    /**
     * Setters
     */
    @Autowired
    public void setQualityControlRepository(QualityControlRepository qualityControlRepository) {
        this.qualityControlRepository = qualityControlRepository;
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
    public List<QualityControlDto> getAll() {
        List<QualityControl> qualityControlList = qualityControlRepository.findAll();
        List<QualityControlDto> qualityControlDtoList = new ArrayList<>();
        for (QualityControl s : qualityControlList) {
            qualityControlDtoList.add(QualityControlMapper.INSTANCE.qualityControlToQualityControlDto(s));
        }
        return qualityControlDtoList;
    }

    @Override
    public QualityControlDto get(Long id) {
        QualityControl qualityControl = qualityControlRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return QualityControlMapper.INSTANCE.qualityControlToQualityControlDto(qualityControl);
    }

    @Override
    public QualityControlDto save(QualityControlDto qualityControlDto) {

        if (qualityControlDto.getQc1PersonId() == null || !personRepository.existsById(qualityControlDto.getQc1PersonId())) {
            throw new EntityNotFoundException("PERSON with ID : '" + qualityControlDto.getQc1PersonId() + "' not found.");
        }
        if (qualityControlDto.getQc1StatusId() == null || !statusRepository.existsById(qualityControlDto.getQc1StatusId())) {
            throw new EntityNotFoundException("STATUS with ID : '" + qualityControlDto.getQc1StatusId() + "' not found.");
        }
        if (qualityControlDto.getMotorcycleId() == null || !motorcycleRepository.existsById(qualityControlDto.getMotorcycleId())) {
            throw new EntityNotFoundException("MOTORCYCLE with ID : '" + qualityControlDto.getMotorcycleId() + "' not found.");
        }

        QualityControl qualityControl = QualityControlMapper.INSTANCE.qualityControlDtoToQualityControl(qualityControlDto);
        qualityControl.setId(null);
        QualityControl qualityControlSaved = qualityControlRepository.save(qualityControl);
        return QualityControlMapper.INSTANCE.qualityControlToQualityControlDto(qualityControlSaved);
    }

    @Override
    public QualityControlDto update(QualityControlDto qualityControlDto) {
        Optional<QualityControl> optionalQualityControl = qualityControlRepository.findById(qualityControlDto.getId());
        if (optionalQualityControl.isEmpty()) {
            throw new EntityNotFoundException("QUALITY_CONTROL with ID : '" + qualityControlDto.getId() + "' not found.");
        }

        if (qualityControlDto.getQc1PersonId() == null) {
            throw new EntityNotFoundException("PERSON ID can't be null");
        }
        if (qualityControlDto.getQc1StatusId() == null) {
            throw new EntityNotFoundException("STATUS ID can't be null");
        }
        if (qualityControlDto.getMotorcycleId() == null) {
            throw new EntityNotFoundException("MOTORCYCLE ID can't be null");
        }

        QualityControl qualityControl = optionalQualityControl.get();
        QualityControlMapper.INSTANCE.updateQualityControlFromDto(qualityControlDto, qualityControl);

        Optional<Person> optionalPersonQc1 = personRepository.findById(qualityControlDto.getQc1PersonId());
        if (optionalPersonQc1.isEmpty()) {
            throw new EntityNotFoundException("PERSON with ID : '" + qualityControlDto.getQc1PersonId() + "' not found.");
        }
        qualityControl.setQc1Person(optionalPersonQc1.get());


        Optional<Status> optionalStatusQc1 = statusRepository.findById(qualityControlDto.getQc1StatusId());
        if (optionalStatusQc1.isEmpty()) {
            throw new EntityNotFoundException("STATUS with ID : '" + qualityControlDto.getQc1StatusId() + "' not found.");
        }
        qualityControl.setQc1Status(optionalStatusQc1.get());

        Optional<Motorcycle> optionalMotorcycle = motorcycleRepository.findById(qualityControlDto.getMotorcycleId());
        if (optionalMotorcycle.isEmpty()) {
            throw new EntityNotFoundException("MOTORCYCLE with ID : '" + qualityControlDto.getMotorcycleId() + "' not found.");
        }
        qualityControl.setMotorcycle(optionalMotorcycle.get());


        if (qualityControlDto.getQc2PersonId() == null) {
            qualityControl.setQc2Person(null);
        } else {
            Optional<Person> optionalPersonQc2 = personRepository.findById(qualityControlDto.getQc2PersonId());
            if (optionalPersonQc2.isEmpty()) {
                throw new EntityNotFoundException("PERSON with ID : '" + qualityControlDto.getQc2PersonId() + "' not found.");
            }
            qualityControl.setQc2Person(optionalPersonQc2.get());
        }

        if (qualityControlDto.getQc2StatusId() == null) {
            qualityControl.setQc2Status(null);
        } else {
            Optional<Status> optionalStatusQc2 = statusRepository.findById(qualityControlDto.getQc2StatusId());
            if (optionalStatusQc2.isEmpty()) {
                throw new EntityNotFoundException("STATUS with ID : '" + qualityControlDto.getQc2StatusId() + "' not found.");
            }
            qualityControl.setQc2Status(optionalStatusQc2.get());
        }


        if (qualityControlDto.getQc3PersonId() == null) {
            qualityControl.setQc3Person(null);
        } else {
            Optional<Person> optionalPersonQc3 = personRepository.findById(qualityControlDto.getQc3PersonId());
            if (optionalPersonQc3.isEmpty()) {
                throw new EntityNotFoundException("PERSON with ID : '" + qualityControlDto.getQc3PersonId() + "' not found.");
            }
            qualityControl.setQc3Person(optionalPersonQc3.get());
        }

        if (qualityControlDto.getQc3StatusId() == null) {
            qualityControl.setQc3Status(null);
        } else {
            Optional<Status> optionalStatusQc3 = statusRepository.findById(qualityControlDto.getQc3StatusId());
            if (optionalStatusQc3.isEmpty()) {
                throw new EntityNotFoundException("STATUS with ID : '" + qualityControlDto.getQc3StatusId() + "' not found.");
            }
            qualityControl.setQc3Status(optionalStatusQc3.get());
        }


        QualityControl qualityControlSaved = qualityControlRepository.save(qualityControl);
        return QualityControlMapper.INSTANCE.qualityControlToQualityControlDto(qualityControlSaved);
    }

    @Override
    public void delete(Long id) {
        QualityControl qualityControl = qualityControlRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        qualityControlRepository.delete(qualityControl);
    }
}

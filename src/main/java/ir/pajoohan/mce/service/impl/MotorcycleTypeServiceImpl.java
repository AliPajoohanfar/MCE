package ir.pajoohan.mce.service.impl;

import ir.pajoohan.mce.dto.MotorcycleTypeDto;
import ir.pajoohan.mce.dto.MotorcycleTypeMapper;
import ir.pajoohan.mce.entity.Attachment;
import ir.pajoohan.mce.entity.MotorcycleType;
import ir.pajoohan.mce.repository.AttachmentRepository;
import ir.pajoohan.mce.repository.MotorcycleTypeRepository;
import ir.pajoohan.mce.service.MotorcycleTypeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MotorcycleTypeServiceImpl implements MotorcycleTypeService {

    MotorcycleTypeRepository motorcycleTypeRepository;
    AttachmentRepository attachmentRepository;

    /**
     * Setters
     */
    @Autowired
    public void setMotorcycleTypeRepository(MotorcycleTypeRepository motorcycleTypeRepository) {
        this.motorcycleTypeRepository = motorcycleTypeRepository;
    }

    @Autowired
    public void setAttachmentRepository(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * Methods
     */
    @Override
    public List<MotorcycleTypeDto> getAll() {
        List<MotorcycleType> motorcycleTypeList = motorcycleTypeRepository.findAll();
        List<MotorcycleTypeDto> motorcycleTypeDtoList = new ArrayList<>();
        for (MotorcycleType s : motorcycleTypeList) {
            motorcycleTypeDtoList.add(MotorcycleTypeMapper.INSTANCE.motorcycleTypeToMotorcycleTypeDto(s));
        }
        return motorcycleTypeDtoList;
    }

    @Override
    public MotorcycleTypeDto get(Long id) {
        MotorcycleType motorcycleType = motorcycleTypeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return MotorcycleTypeMapper.INSTANCE.motorcycleTypeToMotorcycleTypeDto(motorcycleType);
    }

    @Override
    public MotorcycleTypeDto save(MotorcycleTypeDto motorcycleTypeDto) {
        if (motorcycleTypeDto.getInformationAttachId() != null && !attachmentRepository.existsById(motorcycleTypeDto.getInformationAttachId())) {
            throw new EntityNotFoundException("ATTACHMENT with ID : '" + motorcycleTypeDto.getInformationAttachId() + "' not found.");
        }
        MotorcycleType motorcycleType = MotorcycleTypeMapper.INSTANCE.motorcycleTypeDtoToMotorcycleType(motorcycleTypeDto);
        motorcycleType.setId(null);
        MotorcycleType motorcycleTypeSaved = motorcycleTypeRepository.save(motorcycleType);
        return MotorcycleTypeMapper.INSTANCE.motorcycleTypeToMotorcycleTypeDto(motorcycleTypeSaved);
    }

    @Override
    public MotorcycleTypeDto update(MotorcycleTypeDto motorcycleTypeDto) {
        Optional<MotorcycleType> optionalMotorcycleType = motorcycleTypeRepository.findById(motorcycleTypeDto.getId());

        if (optionalMotorcycleType.isEmpty()) {
            throw new EntityNotFoundException("MOTORCYCLE_TYPE with ID : '" + motorcycleTypeDto.getId() + "' not found.");
        }

        MotorcycleType motorcycleType = optionalMotorcycleType.get();
        MotorcycleTypeMapper.INSTANCE.updateMotorcycleTypeFromDto(motorcycleTypeDto, motorcycleType);

        if (motorcycleTypeDto.getInformationAttachId() == null) {
            motorcycleType.setAttachment(null);
        } else {
            Optional<Attachment> optionalAttachment = attachmentRepository.findById(motorcycleTypeDto.getInformationAttachId());
            if (optionalAttachment.isEmpty()) {
                throw new EntityNotFoundException("ATTACHMENT with ID : '" + motorcycleTypeDto.getInformationAttachId() + "' not found.");
            }
            motorcycleType.setAttachment(optionalAttachment.get());
        }

        MotorcycleType motorcycleTypeSaved = motorcycleTypeRepository.save(motorcycleType);
        return MotorcycleTypeMapper.INSTANCE.motorcycleTypeToMotorcycleTypeDto(motorcycleTypeSaved);
    }

    @Override
    public void delete(Long id) {
        MotorcycleType motorcycleType = motorcycleTypeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        motorcycleTypeRepository.delete(motorcycleType);
    }
}
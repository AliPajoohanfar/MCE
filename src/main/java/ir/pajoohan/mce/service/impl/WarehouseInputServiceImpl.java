package ir.pajoohan.mce.service.impl;

import ir.pajoohan.mce.dto.WarehouseInputDto;
import ir.pajoohan.mce.dto.WarehouseInputMapper;
import ir.pajoohan.mce.entity.Attachment;
import ir.pajoohan.mce.entity.EngineType;
import ir.pajoohan.mce.entity.Person;
import ir.pajoohan.mce.entity.WarehouseInput;
import ir.pajoohan.mce.repository.AttachmentRepository;
import ir.pajoohan.mce.repository.EngineTypeRepository;
import ir.pajoohan.mce.repository.PersonRepository;
import ir.pajoohan.mce.repository.WarehouseInputRepository;
import ir.pajoohan.mce.service.WarehouseInputService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WarehouseInputServiceImpl implements WarehouseInputService {

    WarehouseInputRepository warehouseInputRepository;
    AttachmentRepository attachmentRepository;
    EngineTypeRepository engineTypeRepository;
    PersonRepository personRepository;

    /**
     * Setters
     */
    @Autowired
    public void setWarehouseInputRepository(WarehouseInputRepository warehouseInputRepository) {
        this.warehouseInputRepository = warehouseInputRepository;
    }

    @Autowired

    public void setAttachmentRepository(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    @Autowired
    public void setEngineTypeRepository(EngineTypeRepository engineTypeRepository) {
        this.engineTypeRepository = engineTypeRepository;
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
    public List<WarehouseInputDto> getAll() {
        List<WarehouseInput> warehouseInputList = warehouseInputRepository.findAll();
        List<WarehouseInputDto> warehouseInputDtoList = new ArrayList<>();
        for (WarehouseInput s : warehouseInputList) {
            warehouseInputDtoList.add(WarehouseInputMapper.INSTANCE.warehouseInputToWarehouseInputDto(s));
        }
        return warehouseInputDtoList;
    }

    @Override
    public WarehouseInputDto get(Long id) {
        WarehouseInput warehouseInput = warehouseInputRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return WarehouseInputMapper.INSTANCE.warehouseInputToWarehouseInputDto(warehouseInput);
    }

    @Override
    public WarehouseInputDto save(WarehouseInputDto warehouseInputDto) {

        if (warehouseInputDto.getEngineTypeId() == null || !engineTypeRepository.existsById(warehouseInputDto.getEngineTypeId())) {
            throw new EntityNotFoundException("ENGINE_TYPE with ID : '" + warehouseInputDto.getEngineTypeId() + "' not found.");
        }

        if (warehouseInputDto.getIdentifiersAttachId() != null && !attachmentRepository.existsById(warehouseInputDto.getIdentifiersAttachId())) {
            throw new EntityNotFoundException("ATTACHMENT with ID : '" + warehouseInputDto.getIdentifiersAttachId() + "' not found.");
        }
        if (warehouseInputDto.getReceiptsAttachId() != null && !attachmentRepository.existsById(warehouseInputDto.getReceiptsAttachId())) {
            throw new EntityNotFoundException("ATTACHMENT with ID : '" + warehouseInputDto.getReceiptsAttachId() + "' not found.");
        }
        if (warehouseInputDto.getReportsAttachId() != null && !attachmentRepository.existsById(warehouseInputDto.getReportsAttachId())) {
            throw new EntityNotFoundException("ATTACHMENT with ID : '" + warehouseInputDto.getReportsAttachId() + "' not found.");
        }

        if (warehouseInputDto.getControllerPersonId() != null && !personRepository.existsById(warehouseInputDto.getControllerPersonId())) {
            throw new EntityNotFoundException("PERSON with ID : '" + warehouseInputDto.getControllerPersonId() + "' not found.");
        }
        if (warehouseInputDto.getProdPermissionPersonId() != null && !personRepository.existsById(warehouseInputDto.getProdPermissionPersonId())) {
            throw new EntityNotFoundException("PERSON with ID : '" + warehouseInputDto.getProdPermissionPersonId() + "' not found.");
        }

        WarehouseInput warehouseInput = WarehouseInputMapper.INSTANCE.warehouseInputDtoToWarehouseInput(warehouseInputDto);
        warehouseInput.setId(null);
        WarehouseInput warehouseInputSaved = warehouseInputRepository.save(warehouseInput);
        return WarehouseInputMapper.INSTANCE.warehouseInputToWarehouseInputDto(warehouseInputSaved);
    }

    @Override
    public WarehouseInputDto update(WarehouseInputDto warehouseInputDto) {
        Optional<WarehouseInput> optionalWarehouseInput = warehouseInputRepository.findById(warehouseInputDto.getId());
        if (optionalWarehouseInput.isEmpty()) {
            throw new EntityNotFoundException("WAREHOUSE_INPUT with ID : '" + warehouseInputDto.getId() + "' not found.");
        }

        if (warehouseInputDto.getEngineTypeId() == null) {
            throw new EntityNotFoundException("ENGINE_TYPE ID can't be null");
        }

        WarehouseInput warehouseInput = optionalWarehouseInput.get();
        WarehouseInputMapper.INSTANCE.updateWarehouseInputFromDto(warehouseInputDto, warehouseInput);

        Optional<EngineType> optionalEngineType = engineTypeRepository.findById(warehouseInputDto.getEngineTypeId());
        if (optionalEngineType.isEmpty()) {
            throw new EntityNotFoundException("ENGINE_TYPE with ID : '" + warehouseInputDto.getEngineTypeId() + "' not found.");
        }
        warehouseInput.setEngineType(optionalEngineType.get());


        if (warehouseInputDto.getIdentifiersAttachId() == null) {
            warehouseInput.setIdentifiersAttach(null);
        } else {
            Optional<Attachment> optionalAttachment = attachmentRepository.findById(warehouseInputDto.getIdentifiersAttachId());
            if (optionalAttachment.isEmpty()) {
                throw new EntityNotFoundException("ATTACHMENT with ID : '" + warehouseInputDto.getIdentifiersAttachId() + "' not found.");
            }
            warehouseInput.setIdentifiersAttach(optionalAttachment.get());
        }

        if (warehouseInputDto.getReceiptsAttachId() == null) {
            warehouseInput.setReceiptsAttach(null);
        } else {
            Optional<Attachment> optionalAttachment = attachmentRepository.findById(warehouseInputDto.getReceiptsAttachId());
            if (optionalAttachment.isEmpty()) {
                throw new EntityNotFoundException("ATTACHMENT with ID : '" + warehouseInputDto.getReceiptsAttachId() + "' not found.");
            }
            warehouseInput.setReceiptsAttach(optionalAttachment.get());
        }

        if (warehouseInputDto.getReportsAttachId() == null) {
            warehouseInput.setReportsAttach(null);
        } else {
            Optional<Attachment> optionalAttachment = attachmentRepository.findById(warehouseInputDto.getReportsAttachId());
            if (optionalAttachment.isEmpty()) {
                throw new EntityNotFoundException("ATTACHMENT with ID : '" + warehouseInputDto.getReportsAttachId() + "' not found.");
            }
            warehouseInput.setReportsAttach(optionalAttachment.get());
        }

        if (warehouseInputDto.getControllerPersonId() == null) {
            warehouseInput.setControllerPerson(null);
        } else {
            Optional<Person> optionalPerson = personRepository.findById(warehouseInputDto.getControllerPersonId());
            if (optionalPerson.isEmpty()) {
                throw new EntityNotFoundException("PERSON with ID : '" + warehouseInputDto.getControllerPersonId() + "' not found.");
            }
            warehouseInput.setControllerPerson(optionalPerson.get());
        }

        if (warehouseInputDto.getProdPermissionPersonId() == null) {
            warehouseInput.setProdPermissionPerson(null);
        } else {
            Optional<Person> optionalPerson = personRepository.findById(warehouseInputDto.getProdPermissionPersonId());
            if (optionalPerson.isEmpty()) {
                throw new EntityNotFoundException("PERSON with ID : '" + warehouseInputDto.getProdPermissionPersonId() + "' not found.");
            }
            warehouseInput.setProdPermissionPerson(optionalPerson.get());
        }

        WarehouseInput warehouseInputSaved = warehouseInputRepository.save(warehouseInput);
        return WarehouseInputMapper.INSTANCE.warehouseInputToWarehouseInputDto(warehouseInputSaved);
    }

    @Override
    public void delete(Long id) {
        WarehouseInput warehouseInput = warehouseInputRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        warehouseInputRepository.delete(warehouseInput);
    }
}

package ir.pajoohan.mce.service.impl;

import ir.pajoohan.mce.dto.MotorcycleDto;
import ir.pajoohan.mce.dto.MotorcycleMapper;
import ir.pajoohan.mce.entity.Attachment;
import ir.pajoohan.mce.entity.Branch;
import ir.pajoohan.mce.entity.Color;
import ir.pajoohan.mce.entity.Customer;
import ir.pajoohan.mce.entity.Motorcycle;
import ir.pajoohan.mce.entity.MotorcycleType;
import ir.pajoohan.mce.entity.State;
import ir.pajoohan.mce.entity.Status;
import ir.pajoohan.mce.entity.WarehouseInput;
import ir.pajoohan.mce.repository.AttachmentRepository;
import ir.pajoohan.mce.repository.BranchRepository;
import ir.pajoohan.mce.repository.ColorRepository;
import ir.pajoohan.mce.repository.CustomerRepository;
import ir.pajoohan.mce.repository.MotorcycleRepository;
import ir.pajoohan.mce.repository.MotorcycleTypeRepository;
import ir.pajoohan.mce.repository.StateRepository;
import ir.pajoohan.mce.repository.StatusRepository;
import ir.pajoohan.mce.repository.WarehouseInputRepository;
import ir.pajoohan.mce.service.MotorcycleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class MotorcycleServiceImpl implements MotorcycleService {

    MotorcycleRepository motorcycleRepository;
    AttachmentRepository attachmentRepository;
    BranchRepository branchRepository;
    ColorRepository colorRepository;
    CustomerRepository customerRepository;
    MotorcycleTypeRepository motorcycleTypeRepository;
    StateRepository stateRepository;
    StatusRepository statusRepository;
    WarehouseInputRepository warehouseInputRepository;

    /**
     * Setters
     */
    @Autowired
    public void setMotorcycleRepository(MotorcycleRepository motorcycleRepository) {
        this.motorcycleRepository = motorcycleRepository;
    }

    @Autowired
    public void setAttachmentRepository(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    @Autowired
    public void setBranchRepository(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Autowired
    public void setColorRepository(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Autowired
    public void setMotorcycleTypeRepository(MotorcycleTypeRepository motorcycleTypeRepository) {
        this.motorcycleTypeRepository = motorcycleTypeRepository;
    }

    @Autowired
    public void setStateRepository(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Autowired
    public void setStatusRepository(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Autowired
    public void setWarehouseInputRepository(WarehouseInputRepository warehouseInputRepository) {
        this.warehouseInputRepository = warehouseInputRepository;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * Methods
     */
    @Override
    public List<MotorcycleDto> getAll() {
        List<Motorcycle> motorcycleList = motorcycleRepository.findAll();
        List<MotorcycleDto> motorcycleDtoList = new ArrayList<>();
        for (Motorcycle s : motorcycleList) {
            motorcycleDtoList.add(MotorcycleMapper.INSTANCE.motorcycleToMotorcycleDto(s));
        }
        return motorcycleDtoList;
    }

    @Override
    public MotorcycleDto get(Long id) {
        Motorcycle motorcycle = motorcycleRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return MotorcycleMapper.INSTANCE.motorcycleToMotorcycleDto(motorcycle);
    }

    @Override
    public MotorcycleDto save(MotorcycleDto motorcycleDto) {

        if (motorcycleDto.getMotorcycleTypeId() == null || !motorcycleTypeRepository.existsById(motorcycleDto.getMotorcycleTypeId())) {
            throw new EntityNotFoundException("MOTORCYCLE_TYPE with ID : '" + motorcycleDto.getMotorcycleTypeId() + "' not found.");
        }
        if (motorcycleDto.getWarehouseInputId() == null || !warehouseInputRepository.existsById(motorcycleDto.getWarehouseInputId())) {
            throw new EntityNotFoundException("WAREHOUSE_INPUT with ID : '" + motorcycleDto.getWarehouseInputId() + "' not found.");
        }
        if (motorcycleDto.getColorId() == null || !colorRepository.existsById(motorcycleDto.getColorId())) {
            throw new EntityNotFoundException("COLOR with ID : '" + motorcycleDto.getColorId() + "' not found.");
        }
        if (motorcycleDto.getStatusId() == null || !statusRepository.existsById(motorcycleDto.getStatusId())) {
            throw new EntityNotFoundException("STATUS with ID : '" + motorcycleDto.getStatusId() + "' not found.");
        }

        if (motorcycleDto.getBranchId() != null && !branchRepository.existsById(motorcycleDto.getBranchId())) {
            throw new EntityNotFoundException("BRANCH with ID : '" + motorcycleDto.getBranchId() + "' not found.");
        }
        if (motorcycleDto.getCustomerId() != null && !customerRepository.existsById(motorcycleDto.getCustomerId())) {
            throw new EntityNotFoundException("CUSTOMER with ID : '" + motorcycleDto.getCustomerId() + "' not found.");
        }
        if (motorcycleDto.getStateId() != null && !stateRepository.existsById(motorcycleDto.getStateId())) {
            throw new EntityNotFoundException("STATE with ID : '" + motorcycleDto.getStateId() + "' not found.");
        }
        if (motorcycleDto.getAttachmentId() != null && !attachmentRepository.existsById(motorcycleDto.getAttachmentId())) {
            throw new EntityNotFoundException("ATTACHMENT with ID : '" + motorcycleDto.getAttachmentId() + "' not found.");
        }


        Motorcycle motorcycle = MotorcycleMapper.INSTANCE.motorcycleDtoToMotorcycle(motorcycleDto);
        motorcycle.setId(null);
        Motorcycle motorcycleSaved = motorcycleRepository.save(motorcycle);
        return MotorcycleMapper.INSTANCE.motorcycleToMotorcycleDto(motorcycleSaved);
    }

    @Override
    public MotorcycleDto update(MotorcycleDto motorcycleDto) {
        Optional<Motorcycle> optionalMotorcycle = motorcycleRepository.findById(motorcycleDto.getId());
        if (optionalMotorcycle.isEmpty()) {
            throw new EntityNotFoundException("MOTORCYCLE with ID : '" + motorcycleDto.getId() + "' not found.");
        }

        Motorcycle motorcycle = optionalMotorcycle.get();
        MotorcycleMapper.INSTANCE.updateMotorcycleFromDto(motorcycleDto, motorcycle);

        if (motorcycleDto.getMotorcycleTypeId() == null) {
            throw new EntityNotFoundException("MOTORCYCLE_TYPE with ID : '" + motorcycleDto.getMotorcycleTypeId() + "' not found.");
        }
        if (motorcycleDto.getWarehouseInputId() == null) {
            throw new EntityNotFoundException("WAREHOUSE_INPUT with ID : '" + motorcycleDto.getWarehouseInputId() + "' not found.");
        }
        if (motorcycleDto.getColorId() == null) {
            throw new EntityNotFoundException("COLOR with ID : '" + motorcycleDto.getColorId() + "' not found.");
        }
        if (motorcycleDto.getStatusId() == null) {
            throw new EntityNotFoundException("STATUS with ID : '" + motorcycleDto.getStatusId() + "' not found.");
        }

        Optional<MotorcycleType> optionalMotorcycleType = motorcycleTypeRepository.findById(motorcycleDto.getMotorcycleTypeId());
        if (optionalMotorcycleType.isEmpty()) {
            throw new EntityNotFoundException("MOTORCYCLE_TYPE with ID : '" + motorcycleDto.getMotorcycleTypeId() + "' not found.");
        }
        motorcycle.setMotorcycleType(optionalMotorcycleType.get());

        Optional<WarehouseInput> optionalWarehouseInput = warehouseInputRepository.findById(motorcycleDto.getWarehouseInputId());
        if (optionalWarehouseInput.isEmpty()) {
            throw new EntityNotFoundException("WAREHOUSE_INPUT with ID : '" + motorcycleDto.getWarehouseInputId() + "' not found.");
        }
        motorcycle.setWarehouseInput(optionalWarehouseInput.get());

        Optional<Color> optionalColor = colorRepository.findById(motorcycleDto.getColorId());
        if (optionalColor.isEmpty()) {
            throw new EntityNotFoundException("COLOR with ID : '" + motorcycleDto.getColorId() + "' not found.");
        }
        motorcycle.setColor(optionalColor.get());

        Optional<Status> optionalStatus = statusRepository.findById(motorcycleDto.getStatusId());
        if (optionalStatus.isEmpty()) {
            throw new EntityNotFoundException("STATUS with ID : '" + motorcycleDto.getStatusId() + "' not found.");
        }
        motorcycle.setStatus(optionalStatus.get());


        if (motorcycleDto.getBranchId() == null) {
            motorcycle.setBranch(null);
        } else {
            Optional<Branch> optionalBranch = branchRepository.findById(motorcycleDto.getBranchId());
            if (optionalBranch.isEmpty()) {
                throw new EntityNotFoundException("BRANCH with ID : '" + motorcycleDto.getBranchId() + "' not found.");
            }
            motorcycle.setBranch(optionalBranch.get());
        }

        if (motorcycleDto.getCustomerId() == null) {
            motorcycle.setCustomer(null);
        } else {
            Optional<Customer> optionalCustomer = customerRepository.findById(motorcycleDto.getCustomerId());
            if (optionalCustomer.isEmpty()) {
                throw new EntityNotFoundException("CUSTOMER with ID : '" + motorcycleDto.getCustomerId() + "' not found.");
            }
            motorcycle.setCustomer(optionalCustomer.get());
        }

        if (motorcycleDto.getStateId() == null) {
            motorcycle.setState(null);
        } else {
            Optional<State> optionalState = stateRepository.findById(motorcycleDto.getStateId());
            if (optionalState.isEmpty()) {
                throw new EntityNotFoundException("STATE with ID : '" + motorcycleDto.getStateId() + "' not found.");
            }
            motorcycle.setState(optionalState.get());
        }

        if (motorcycleDto.getAttachmentId() == null) {
            motorcycle.setAttachment(null);
        } else {
            Optional<Attachment> optionalAttachment = attachmentRepository.findById(motorcycleDto.getAttachmentId());
            if (optionalAttachment.isEmpty()) {
                throw new EntityNotFoundException("ATTACHMENT with ID : '" + motorcycleDto.getAttachmentId() + "' not found.");
            }
            motorcycle.setAttachment(optionalAttachment.get());
        }


        Motorcycle motorcycleSaved = motorcycleRepository.save(motorcycle);
        return MotorcycleMapper.INSTANCE.motorcycleToMotorcycleDto(motorcycleSaved);
    }

    @Override
    public void delete(Long id) {
        Motorcycle motorcycle = motorcycleRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        motorcycleRepository.delete(motorcycle);
    }
}

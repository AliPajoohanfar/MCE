package ir.pajoohan.mce.service.impl;

import ir.pajoohan.mce.dto.AftersalesServiceDto;
import ir.pajoohan.mce.dto.AftersalesServiceMapper;
import ir.pajoohan.mce.entity.AftersalesService;
import ir.pajoohan.mce.entity.Branch;
import ir.pajoohan.mce.entity.Customer;
import ir.pajoohan.mce.entity.Motorcycle;
import ir.pajoohan.mce.repository.AftersalesServiceRepository;
import ir.pajoohan.mce.repository.BranchRepository;
import ir.pajoohan.mce.repository.CustomerRepository;
import ir.pajoohan.mce.repository.MotorcycleRepository;
import ir.pajoohan.mce.service.AftersalesServiceService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AftersalesServiceServiceImpl implements AftersalesServiceService {

    AftersalesServiceRepository aftersalesServiceRepository;
    BranchRepository branchRepository;
    CustomerRepository customerRepository;
    MotorcycleRepository motorcycleRepository;

    /**
     * Setters
     */
    @Autowired
    public void setAftersalesServiceRepository(AftersalesServiceRepository aftersalesServiceRepository) {
        this.aftersalesServiceRepository = aftersalesServiceRepository;
    }

    @Autowired

    public void setBranchRepository(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Autowired

    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
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
    public List<AftersalesServiceDto> getAll() {
        List<AftersalesService> aftersalesServiceList = aftersalesServiceRepository.findAll();
        List<AftersalesServiceDto> aftersalesServiceDtoList = new ArrayList<>();
        for (AftersalesService s : aftersalesServiceList) {
            aftersalesServiceDtoList.add(AftersalesServiceMapper.INSTANCE.aftersalesServiceToAftersalesServiceDto(s));
        }
        return aftersalesServiceDtoList;
    }

    @Override
    public AftersalesServiceDto get(Long id) {
        AftersalesService aftersalesService = aftersalesServiceRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return AftersalesServiceMapper.INSTANCE.aftersalesServiceToAftersalesServiceDto(aftersalesService);
    }

    @Override
    public AftersalesServiceDto save(AftersalesServiceDto aftersalesServiceDto) {

        if (aftersalesServiceDto.getBranchId() == null || !branchRepository.existsById(aftersalesServiceDto.getBranchId())) {
            throw new EntityNotFoundException("BRANCH with ID : '" + aftersalesServiceDto.getBranchId() + "' not found.");
        }
        if (aftersalesServiceDto.getCustomerId() == null || !customerRepository.existsById(aftersalesServiceDto.getCustomerId())) {
            throw new EntityNotFoundException("CUSTOMER with ID : '" + aftersalesServiceDto.getCustomerId() + "' not found.");
        }
        if (aftersalesServiceDto.getMotorcycleId() == null || !motorcycleRepository.existsById(aftersalesServiceDto.getMotorcycleId())) {
            throw new EntityNotFoundException("MOTORCYCLE with ID : '" + aftersalesServiceDto.getMotorcycleId() + "' not found.");
        }

        AftersalesService aftersalesService = AftersalesServiceMapper.INSTANCE.aftersalesServiceDtoToAftersalesService(aftersalesServiceDto);
        aftersalesService.setId(null);
        AftersalesService aftersalesServiceSaved = aftersalesServiceRepository.save(aftersalesService);
        return AftersalesServiceMapper.INSTANCE.aftersalesServiceToAftersalesServiceDto(aftersalesServiceSaved);
    }

    @Override
    public AftersalesServiceDto update(AftersalesServiceDto aftersalesServiceDto) {
        Optional<AftersalesService> optionalAftersalesService = aftersalesServiceRepository.findById(aftersalesServiceDto.getId());
        if (optionalAftersalesService.isEmpty()) {
            throw new EntityNotFoundException("AFTERSALES_SERVICE with ID : '" + aftersalesServiceDto.getId() + "' not found.");
        }

        if (aftersalesServiceDto.getBranchId() == null) {
            throw new EntityNotFoundException("BRANCH ID can't be null");
        }
        if (aftersalesServiceDto.getCustomerId() == null) {
            throw new EntityNotFoundException("CUSTOMER ID can't be null");
        }
        if (aftersalesServiceDto.getMotorcycleId() == null) {
            throw new EntityNotFoundException("MOTORCYCLE ID can't be null");
        }

        AftersalesService aftersalesService = optionalAftersalesService.get();
        AftersalesServiceMapper.INSTANCE.updateAftersalesServiceFromDto(aftersalesServiceDto, aftersalesService);

        Optional<Branch> optionalBranch = branchRepository.findById(aftersalesServiceDto.getBranchId());
        if (optionalBranch.isEmpty()) {
            throw new EntityNotFoundException("BRANCH with ID : '" + aftersalesServiceDto.getBranchId() + "' not found.");
        }
        aftersalesService.setBranch(optionalBranch.get());

        Optional<Customer> optionalCustomer = customerRepository.findById(aftersalesServiceDto.getCustomerId());
        if (optionalCustomer.isEmpty()) {
            throw new EntityNotFoundException("CUSTOMER with ID : '" + aftersalesServiceDto.getBranchId() + "' not found.");
        }
        aftersalesService.setCustomer(optionalCustomer.get());

        Optional<Motorcycle> optionalMotorcycle = motorcycleRepository.findById(aftersalesServiceDto.getMotorcycleId());
        if (optionalMotorcycle.isEmpty()) {
            throw new EntityNotFoundException("MOTORCYCLE with ID : '" + aftersalesServiceDto.getBranchId() + "' not found.");
        }
        aftersalesService.setMotorcycle(optionalMotorcycle.get());

        AftersalesService aftersalesServiceSaved = aftersalesServiceRepository.save(aftersalesService);
        return AftersalesServiceMapper.INSTANCE.aftersalesServiceToAftersalesServiceDto(aftersalesServiceSaved);
    }

    @Override
    public void delete(Long id) {
        AftersalesService aftersalesService = aftersalesServiceRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        aftersalesServiceRepository.delete(aftersalesService);
    }
}

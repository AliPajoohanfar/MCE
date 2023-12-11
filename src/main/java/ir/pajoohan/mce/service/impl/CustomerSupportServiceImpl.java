package ir.pajoohan.mce.service.impl;

import ir.pajoohan.mce.dto.CustomerSupportDto;
import ir.pajoohan.mce.dto.CustomerSupportMapper;
import ir.pajoohan.mce.entity.CustomerSupport;
import ir.pajoohan.mce.repository.CustomerSupportRepository;
import ir.pajoohan.mce.service.CustomerSupportService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerSupportServiceImpl implements CustomerSupportService {

    CustomerSupportRepository customerSupportRepository;

    /**
     * Setters
     */
    @Autowired
    public void setCustomerSupportRepository(CustomerSupportRepository customerSupportRepository) {
        this.customerSupportRepository = customerSupportRepository;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * Methods
     */
    @Override
    public List<CustomerSupportDto> getAll() {
        List<CustomerSupport> customerSupportList = customerSupportRepository.findAll();
        List<CustomerSupportDto> customerSupportDtoList = new ArrayList<>();
        for (CustomerSupport s : customerSupportList) {
            customerSupportDtoList.add(CustomerSupportMapper.INSTANCE.customerSupportToCustomerSupportDto(s));
        }
        return customerSupportDtoList;
    }

    @Override
    public CustomerSupportDto get(Long id) {
        CustomerSupport customerSupport = customerSupportRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return CustomerSupportMapper.INSTANCE.customerSupportToCustomerSupportDto(customerSupport);
    }

    @Override
    public CustomerSupportDto save(CustomerSupportDto customerSupportDto) {
        if (customerSupportDto.getParentId() != null && !customerSupportRepository.existsById(customerSupportDto.getParentId())) {
            throw new EntityNotFoundException("PARENT with ID : '" + customerSupportDto.getParentId() + "' not found.");
        }

        CustomerSupport customerSupport = CustomerSupportMapper.INSTANCE.customerSupportDtoToCustomerSupport(customerSupportDto);
        customerSupport.setId(null);
        CustomerSupport customerSupportSaved = customerSupportRepository.save(customerSupport);
        return CustomerSupportMapper.INSTANCE.customerSupportToCustomerSupportDto(customerSupportSaved);
    }

    @Override
    public CustomerSupportDto update(CustomerSupportDto customerSupportDto) {
        Optional<CustomerSupport> optionalCustomerSupport = customerSupportRepository.findById(customerSupportDto.getId());
        if (optionalCustomerSupport.isEmpty()) {
            throw new EntityNotFoundException("CUSTOMER_SUPPORT with ID : '" + customerSupportDto.getId() + "' not found.");
        }

        CustomerSupport customerSupport = optionalCustomerSupport.get();
        CustomerSupportMapper.INSTANCE.updateCustomerSupportFromDto(customerSupportDto, customerSupport);

        if (customerSupportDto.getParentId() == null) {
            customerSupport.setParent(null);
        } else {
            Optional<CustomerSupport> optionalParentCustomerSupport = customerSupportRepository.findById(customerSupportDto.getParentId());
            if (optionalParentCustomerSupport.isEmpty()) {
                throw new EntityNotFoundException("PARENT with ID : '" + customerSupportDto.getParentId() + "' not found.");
            }
            customerSupport.setParent(optionalParentCustomerSupport.get());
        }

        CustomerSupport customerSupportSaved = customerSupportRepository.save(customerSupport);
        return CustomerSupportMapper.INSTANCE.customerSupportToCustomerSupportDto(customerSupportSaved);
    }

    @Override
    public void delete(Long id) {
        CustomerSupport customerSupport = customerSupportRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        customerSupportRepository.delete(customerSupport);
    }
}

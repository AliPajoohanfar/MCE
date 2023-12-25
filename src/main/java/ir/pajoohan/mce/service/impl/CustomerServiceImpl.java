package ir.pajoohan.mce.service.impl;

import ir.pajoohan.mce.dto.CustomerDto;
import ir.pajoohan.mce.dto.CustomerMapper;
import ir.pajoohan.mce.entity.Customer;
import ir.pajoohan.mce.repository.CustomerRepository;
import ir.pajoohan.mce.service.CustomerService;
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
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;

    /**
     * Setters
     */
    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * Methods
     */
    @Override
    public Page<CustomerDto> getAll(Integer page, Integer size, String sort) {

        Pageable pageable = PageRequest.of(page, size).withSort(Sort.Direction.ASC, sort);
        Page<Customer> customerPage = customerRepository.findAll(pageable);
        List<Customer> customerList = customerPage.stream().toList();

        List<CustomerDto> customerDtoList = new ArrayList<>();
        for (Customer c : customerList) {
            customerDtoList.add(CustomerMapper.INSTANCE.customerToCustomerDto(c));
        }
        Page<CustomerDto> customerDtoPage = new PageImpl<>(customerDtoList, pageable, customerPage.getTotalElements());

        return customerDtoPage;
    }

    @Override
    public CustomerDto get(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return CustomerMapper.INSTANCE.customerToCustomerDto(customer);
    }

    @Override
    public CustomerDto save(CustomerDto customerDto) {
        Customer customer = CustomerMapper.INSTANCE.customerDtoToCustomer(customerDto);
        customer.setId(null);
        Customer customerSaved = customerRepository.save(customer);
        return CustomerMapper.INSTANCE.customerToCustomerDto(customerSaved);
    }

    @Override
    public CustomerDto update(CustomerDto customerDto) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerDto.getId());

        if (optionalCustomer.isEmpty()) {
            throw new EntityNotFoundException("CUSTOMER with ID : '" + customerDto.getId() + "' not found.");
        }
        Customer customer = optionalCustomer.get();
        CustomerMapper.INSTANCE.updateCustomerFromDto(customerDto, customer);
        Customer customerSaved = customerRepository.save(customer);
        return CustomerMapper.INSTANCE.customerToCustomerDto(customerSaved);
    }

    @Override
    public void delete(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        customerRepository.delete(customer);
    }
}

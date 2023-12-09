package ir.pajoohan.mce.service;

import ir.pajoohan.mce.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> getAll();

    CustomerDto get(Long id);

    CustomerDto save(CustomerDto customerDto);

    CustomerDto update(CustomerDto customerDto);

    void delete(Long id);
}

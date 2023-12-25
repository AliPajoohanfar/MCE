package ir.pajoohan.mce.service;

import ir.pajoohan.mce.dto.CustomerDto;
import org.springframework.data.domain.Page;

public interface CustomerService {

    Page<CustomerDto> getAll(Integer page, Integer size, String sort);

    CustomerDto get(Long id);

    CustomerDto save(CustomerDto customerDto);

    CustomerDto update(CustomerDto customerDto);

    void delete(Long id);
}

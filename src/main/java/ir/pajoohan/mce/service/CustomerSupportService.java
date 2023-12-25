package ir.pajoohan.mce.service;

import ir.pajoohan.mce.dto.CustomerSupportDto;
import org.springframework.data.domain.Page;

public interface CustomerSupportService {

    Page<CustomerSupportDto> getAll(Integer page, Integer size, String sort);

    CustomerSupportDto get(Long id);

    CustomerSupportDto save(CustomerSupportDto customerSupportDto);

    CustomerSupportDto update(CustomerSupportDto customerSupportDto);

    void delete(Long id);
}

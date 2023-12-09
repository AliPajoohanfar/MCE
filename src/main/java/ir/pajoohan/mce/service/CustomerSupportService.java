package ir.pajoohan.mce.service;

import ir.pajoohan.mce.dto.CustomerSupportDto;

import java.util.List;

public interface CustomerSupportService {

    List<CustomerSupportDto> getAll();

    CustomerSupportDto get(Long id);

    CustomerSupportDto save(CustomerSupportDto customerSupportDto);

    CustomerSupportDto update(CustomerSupportDto customerSupportDto);

    void delete(Long id);
}

package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.AddEffectiveMapping;
import ir.pajoohan.mce.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @AddEffectiveMapping
    CustomerDto customerToCustomerDto(Customer customer);

    @Mapping(target = "effectiveDate", source = "effectiveDate")
    Customer customerDtoToCustomer(CustomerDto customerDto);
}

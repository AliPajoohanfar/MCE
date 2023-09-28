package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.entity.CustomerSupport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CustomerSupportMapper {

    @Mapping(target = "parentId", source = "customerSupport.parent.id")
    CustomerSupportDto customerSupportToCustomerSupportDto(CustomerSupport customerSupport);

    @Mapping(target = "parent.id", source = "customerSupportDto.parentId")
    CustomerSupport customerSupportDtoToCustomerSupport(CustomerSupportDto customerSupportDto);
}

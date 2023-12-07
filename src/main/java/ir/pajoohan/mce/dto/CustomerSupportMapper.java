package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.AddEffectiveMapping;
import ir.pajoohan.mce.dto.baseDto.AddUpdateMapping;
import ir.pajoohan.mce.entity.CustomerSupport;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface CustomerSupportMapper {

    @AddEffectiveMapping
    @Mapping(target = "parentId", source = "customerSupport.parent.id")
    CustomerSupportDto customerSupportToCustomerSupportDto(CustomerSupport customerSupport);

    @Mapping(target = "effectiveDate", source = "effectiveDate")
    @Mapping(target = "parent.id", source = "customerSupportDto.parentId")
    CustomerSupport customerSupportDtoToCustomerSupport(CustomerSupportDto customerSupportDto);

    @AddUpdateMapping
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    void updateCustomerSupportFromDto(CustomerSupportDto customerSupportDto, @MappingTarget CustomerSupport customerSupport);
}

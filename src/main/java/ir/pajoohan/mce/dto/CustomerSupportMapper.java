package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.AddEffectiveMapping;
import ir.pajoohan.mce.dto.baseDto.AddUpdateMapping;
import ir.pajoohan.mce.entity.CustomerSupport;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public abstract class CustomerSupportMapper {

    public static CustomerSupportMapper INSTANCE = Mappers.getMapper(CustomerSupportMapper.class);

    @AddEffectiveMapping
    @Mapping(target = "parentId", source = "customerSupport.parent.id")
    public abstract CustomerSupportDto customerSupportToCustomerSupportDto(CustomerSupport customerSupport);

    @Mapping(target = "effectiveDate", source = "effectiveDate")
    @Mapping(target = "parent.id", source = "customerSupportDto.parentId")
    public abstract CustomerSupport customerSupportDtoToCustomerSupport(CustomerSupportDto customerSupportDto);

    @AddUpdateMapping
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    public abstract void updateCustomerSupportFromDto(CustomerSupportDto customerSupportDto, @MappingTarget CustomerSupport customerSupport);

    @AfterMapping
    protected CustomerSupport doAfterMapping(@MappingTarget CustomerSupport customerSupport) {
        if (customerSupport != null && customerSupport.getParent() != null && customerSupport.getParent().getId() == null) {
            customerSupport.setParent(null);
        }
        return customerSupport;
    }
}

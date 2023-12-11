package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.AddEffectiveMapping;
import ir.pajoohan.mce.dto.baseDto.AddUpdateMapping;
import ir.pajoohan.mce.entity.AftersalesService;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface AftersalesServiceMapper {

    AftersalesServiceMapper INSTANCE = Mappers.getMapper(AftersalesServiceMapper.class);

    @AddEffectiveMapping
    @Mapping(target = "customerId", source = "aftersalesService.customer.id")
    @Mapping(target = "motorcycleId", source = "aftersalesService.motorcycle.id")
    @Mapping(target = "branchId", source = "aftersalesService.branch.id")
    AftersalesServiceDto aftersalesServiceToAftersalesServiceDto(AftersalesService aftersalesService);

    @Mapping(target = "effectiveDate", source = "effectiveDate")
    @Mapping(target = "customer.id", source = "aftersalesServiceDto.customerId")
    @Mapping(target = "motorcycle.id", source = "aftersalesServiceDto.motorcycleId")
    @Mapping(target = "branch.id", source = "aftersalesServiceDto.branchId")
    AftersalesService aftersalesServiceDtoToAftersalesService(AftersalesServiceDto aftersalesServiceDto);

    @AddUpdateMapping
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    void updateAftersalesServiceFromDto(AftersalesServiceDto aftersalesServiceDto, @MappingTarget AftersalesService aftersalesService);
}

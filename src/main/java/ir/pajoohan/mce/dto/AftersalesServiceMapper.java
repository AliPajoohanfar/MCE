package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.AddEffectiveMapping;
import ir.pajoohan.mce.entity.AftersalesService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AftersalesServiceMapper {

    AftersalesServiceMapper INSTANCE = Mappers.getMapper(AftersalesServiceMapper.class);

    @AddEffectiveMapping
    @Mapping(target = "customerId", source = "aftersalesService.customer.id")
    @Mapping(target = "motorcycleId", source = "aftersalesService.motorcycle.id")
    @Mapping(target = "branchId", source = "aftersalesService.branch.id")
    AftersalesServiceDto AftersalesServiceToAftersalesServiceDto(AftersalesService aftersalesService);

    @Mapping(target = "effectiveDate", source = "effectiveDate")
    @Mapping(target = "customer.id", source = "aftersalesServiceDto.customerId")
    @Mapping(target = "motorcycle.id", source = "aftersalesServiceDto.motorcycleId")
    @Mapping(target = "branch.id", source = "aftersalesServiceDto.branchId")
    AftersalesService AftersalesServiceDtoToAftersalesService(AftersalesServiceDto aftersalesServiceDto);

}

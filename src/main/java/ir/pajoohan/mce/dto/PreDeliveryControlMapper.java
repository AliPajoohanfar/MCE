package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.AddEffectiveMapping;
import ir.pajoohan.mce.dto.baseDto.AddUpdateMapping;
import ir.pajoohan.mce.entity.PreDeliveryControl;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface PreDeliveryControlMapper {

    PreDeliveryControlMapper INSTANCE = Mappers.getMapper(PreDeliveryControlMapper.class);

    @AddEffectiveMapping
    @Mapping(target = "personId", source = "person.id")
    @Mapping(target = "statusId", source = "status.id")
    @Mapping(target = "motorcycleId", source = "motorcycle.id")
    PreDeliveryControlDto PreDeliveryControlToPreDeliveryControlDto(PreDeliveryControl preDeliveryControl);

    @Mapping(target = "effectiveDate", source = "effectiveDate")
    @Mapping(target = "person.id", source = "personId")
    @Mapping(target = "status.id", source = "statusId")
    @Mapping(target = "motorcycle.id", source = "motorcycleId")
    PreDeliveryControl PreDeliveryControlDtoToPreDeliveryControl(PreDeliveryControlDto preDeliveryControlDto);

    @AddUpdateMapping
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    void updatePreDeliveryControlFromDto(PreDeliveryControlDto preDeliveryControlDto, @MappingTarget PreDeliveryControl preDeliveryControl);
}
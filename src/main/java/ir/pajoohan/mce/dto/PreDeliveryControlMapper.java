package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.entity.PreDeliveryControl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PreDeliveryControlMapper {

    PreDeliveryControlMapper INSTANCE = Mappers.getMapper(PreDeliveryControlMapper.class);

    @Mapping(target = "personId", source = "person.id")
    @Mapping(target = "statusId", source = "status.id")
    @Mapping(target = "motorcycleId", source = "motorcycle.id")
    PreDeliveryControlDto PreDeliveryControlToPreDeliveryControlDto(PreDeliveryControl preDeliveryControl);


    @Mapping(target = "person.id", source = "personId")
    @Mapping(target = "status.id", source = "statusId")
    @Mapping(target = "motorcycle.id", source = "motorcycleId")
    PreDeliveryControl PreDeliveryControlDtoToPreDeliveryControl(PreDeliveryControlDto preDeliveryControlDto);

}
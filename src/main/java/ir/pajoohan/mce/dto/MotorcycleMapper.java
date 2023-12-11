package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.AddEffectiveMapping;
import ir.pajoohan.mce.dto.baseDto.AddUpdateMapping;
import ir.pajoohan.mce.entity.Motorcycle;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface MotorcycleMapper {

    MotorcycleMapper INSTANCE = Mappers.getMapper(MotorcycleMapper.class);

    @AddEffectiveMapping
    @Mapping(target = "motorcycleTypeId", source = "motorcycle.motorcycleType.id")
    @Mapping(target = "branchId", source = "motorcycle.branch.id")
    @Mapping(target = "customerId", source = "motorcycle.customer.id")
    @Mapping(target = "warehouseInputId", source = "motorcycle.warehouseInput.id")
    @Mapping(target = "colorId", source = "motorcycle.color.id")
    @Mapping(target = "statusId", source = "motorcycle.status.id")
    @Mapping(target = "stateId", source = "motorcycle.state.id")
    @Mapping(target = "attachmentId", source = "motorcycle.attachment.id")
    MotorcycleDto MotorcycleToMotorcycleDto(Motorcycle motorcycle);

    @Mapping(target = "effectiveDate", source = "effectiveDate")
    @Mapping(target = "motorcycleType.id", source = "motorcycleTypeId")
    @Mapping(target = "branch.id", source = "branchId")
    @Mapping(target = "customer.id", source = "customerId")
    @Mapping(target = "warehouseInput.id", source = "warehouseInputId")
    @Mapping(target = "color.id", source = "colorId")
    @Mapping(target = "status.id", source = "statusId")
    @Mapping(target = "state.id", source = "stateId")
    @Mapping(target = "attachment.id", source = "attachmentId")
    Motorcycle MotorcycleDtoToMotorcycle(MotorcycleDto motorcycleDto);

    @AddUpdateMapping
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    void updateMotorcycleFromDto(MotorcycleDto motorcycleDto, @MappingTarget Motorcycle motorcycle);
}

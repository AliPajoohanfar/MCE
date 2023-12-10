package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.AddEffectiveMapping;
import ir.pajoohan.mce.dto.baseDto.AddUpdateMapping;
import ir.pajoohan.mce.entity.MotorcycleType;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public abstract class MotorcycleTypeMapper {

    public static MotorcycleTypeMapper INSTANCE = Mappers.getMapper(MotorcycleTypeMapper.class);

    @AddEffectiveMapping
    @Mapping(target = "informationAttachId", source = "motorcycleType.attachment.id")
    public abstract MotorcycleTypeDto motorcycleTypeToMotorcycleTypeDto(MotorcycleType motorcycleType);

    @Mapping(target = "effectiveDate", source = "effectiveDate")
    @Mapping(target = "attachment.id", source = "motorcycleTypeDto.informationAttachId", nullValuePropertyMappingStrategy = IGNORE)
    public abstract MotorcycleType motorcycleTypeDtoToMotorcycleType(MotorcycleTypeDto motorcycleTypeDto);

    @AddUpdateMapping
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    public abstract void updateMotorcycleTypeFromDto(MotorcycleTypeDto motorcycleTypeDto, @MappingTarget MotorcycleType motorcycleType);

    @AfterMapping
    protected MotorcycleType doAfterMapping(@MappingTarget MotorcycleType motorcycleType) {
        if (motorcycleType != null && motorcycleType.getAttachment() != null && motorcycleType.getAttachment().getId() == null) {
            motorcycleType.setAttachment(null);
        }
        return motorcycleType;
    }
}

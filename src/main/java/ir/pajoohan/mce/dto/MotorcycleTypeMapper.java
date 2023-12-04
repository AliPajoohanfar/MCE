package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.AddEffectiveMapping;
import ir.pajoohan.mce.entity.MotorcycleType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MotorcycleTypeMapper {

    MotorcycleTypeMapper INSTANCE = Mappers.getMapper(MotorcycleTypeMapper.class);

    @AddEffectiveMapping
    @Mapping(target = "informationAttachId", source = "motorcycleType.attachment.id")
    MotorcycleTypeDto motorcycleTypeToMotorcycleTypeDto(MotorcycleType motorcycleType);

    @Mapping(target = "effectiveDate", source = "effectiveDate")
    @Mapping(target = "attachment.id", source = "motorcycleTypeDto.informationAttachId")
    MotorcycleType motorcycleTypeDtoToMotorcycleType(MotorcycleTypeDto motorcycleTypeDto);
}

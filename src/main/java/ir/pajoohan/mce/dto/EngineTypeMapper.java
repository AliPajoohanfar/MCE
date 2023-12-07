package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.AddEffectiveMapping;
import ir.pajoohan.mce.dto.baseDto.AddUpdateMapping;
import ir.pajoohan.mce.entity.EngineType;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface EngineTypeMapper {

    EngineTypeMapper INSTANCE = Mappers.getMapper(EngineTypeMapper.class);

    @AddEffectiveMapping
    EngineTypeDto engineTypeToEngineTypeDto(EngineType engineType);

    @Mapping(target = "effectiveDate", source = "effectiveDate")
    EngineType engineTypeDtoToEngineType(EngineTypeDto engineTypeDto);

    @AddUpdateMapping
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    void updateEngineTypeFromDto(EngineTypeDto engineTypeDto, @MappingTarget EngineType engineType);
}
package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.AddEffectiveMapping;
import ir.pajoohan.mce.entity.EngineType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EngineTypeMapper {

    EngineTypeMapper INSTANCE = Mappers.getMapper(EngineTypeMapper.class);

    @AddEffectiveMapping
    EngineTypeDto engineTypeToEngineTypeDto(EngineType engineType);

    @Mapping(target = "effectiveDate", source = "effectiveDate")
    EngineType engineTypeDtoToEngineType(EngineTypeDto engineTypeDto);
}
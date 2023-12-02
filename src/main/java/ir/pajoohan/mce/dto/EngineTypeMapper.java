package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.AddAuditMapping;
import ir.pajoohan.mce.entity.EngineType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EngineTypeMapper {

    EngineTypeMapper INSTANCE = Mappers.getMapper(EngineTypeMapper.class);

    @AddAuditMapping
    EngineTypeDto engineTypeToEngineTypeDto(EngineType engineType);

    EngineType engineTypeDtoToEngineType(EngineTypeDto engineTypeDto);
}
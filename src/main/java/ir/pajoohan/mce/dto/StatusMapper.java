package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.AddEffectiveMapping;
import ir.pajoohan.mce.entity.State;
import ir.pajoohan.mce.entity.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StatusMapper {

    StatusMapper INSTANCE = Mappers.getMapper(StatusMapper.class);

    @AddEffectiveMapping
    StatusDto statusToStatusDto(Status status);

    @Mapping(target = "effectiveDate", source = "effectiveDate")
    State statusDtoToStatus(StatusDto statusDto);
}

package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.entity.State;
import ir.pajoohan.mce.entity.Status;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StatusMapper {

    StatusMapper INSTANCE = Mappers.getMapper(StatusMapper.class);

    StatusDto statusToStatusDto(Status status);

    State statusDtoToStatus(StatusDto statusDto);
}

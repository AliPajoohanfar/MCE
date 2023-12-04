package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.AddEffectiveMapping;
import ir.pajoohan.mce.entity.State;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StateMapper {

    StateMapper INSTANCE = Mappers.getMapper(StateMapper.class);

    @AddEffectiveMapping
    StateDto stateToStateDto(State state);

    @Mapping(target = "effectiveDate", source = "effectiveDate")
    State stateDtoToState(StateDto stateDto);
}

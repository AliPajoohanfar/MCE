package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.entity.State;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StateMapper {

    StateMapper INSTANCE = Mappers.getMapper(StateMapper.class);

    StateDto stateToStateDto(State state);

    State stateDtoToState(StateDto stateDto);
}
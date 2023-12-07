package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.AddEffectiveMapping;
import ir.pajoohan.mce.dto.baseDto.AddUpdateMapping;
import ir.pajoohan.mce.entity.State;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface StateMapper {

    StateMapper INSTANCE = Mappers.getMapper(StateMapper.class);

    @AddEffectiveMapping
    StateDto stateToStateDto(State state);

    @Mapping(target = "effectiveDate", source = "effectiveDate")
    State stateDtoToState(StateDto stateDto);

    @AddUpdateMapping
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    void updateStateFromDto(StateDto stateDto, @MappingTarget State state);

}

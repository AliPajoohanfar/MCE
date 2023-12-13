package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.AddEffectiveMapping;
import ir.pajoohan.mce.dto.baseDto.AddUpdateMapping;
import ir.pajoohan.mce.entity.FinalControl;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface FinalControlMapper {

    FinalControlMapper INSTANCE = Mappers.getMapper(FinalControlMapper.class);

    @AddEffectiveMapping
    @Mapping(target = "personId", source = "person.id")
    @Mapping(target = "statusId", source = "status.id")
    @Mapping(target = "motorcycleId", source = "motorcycle.id")
    FinalControlDto finalControlToFinalControlDto(FinalControl finalControl);

    @Mapping(target = "effectiveDate", source = "effectiveDate")
    @Mapping(target = "person.id", source = "personId")
    @Mapping(target = "status.id", source = "statusId")
    @Mapping(target = "motorcycle.id", source = "motorcycleId")
    FinalControl finalControlDtoToFinalControl(FinalControlDto finalControlDto);

    @AddUpdateMapping
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    void updateFinalControlFromDto(FinalControlDto finalControlDto, @MappingTarget FinalControl finalControl);
}

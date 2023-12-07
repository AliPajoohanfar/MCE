package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.AddEffectiveMapping;
import ir.pajoohan.mce.dto.baseDto.AddUpdateMapping;
import ir.pajoohan.mce.entity.Color;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface ColorMapper {
    ColorMapper INSTANCE = Mappers.getMapper(ColorMapper.class);

    @AddEffectiveMapping
    ColorDto colorToColorDto(Color color);

    @Mapping(target = "effectiveDate", source = "effectiveDate")
    Color colorDtoToColor(ColorDto colorDto);

    @AddUpdateMapping
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    void updateColorFromDto(ColorDto colorDto, @MappingTarget Color color);
}
package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.AddEffectiveMapping;
import ir.pajoohan.mce.entity.Color;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ColorMapper {
    ColorMapper INSTANCE = Mappers.getMapper(ColorMapper.class);

    @AddEffectiveMapping
    ColorDto colorToColorDto(Color color);

    @Mapping(target = "effectiveDate", source = "effectiveDate")
    Color colorDtoToColor(ColorDto colorDto);
}
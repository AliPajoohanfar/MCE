package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.entity.Color;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ColorMapper {
    ColorMapper INSTANCE = Mappers.getMapper(ColorMapper.class);

    ColorDto colorToColorDto(Color color);

    Color colorDtoToColor(ColorDto colorDto);
}
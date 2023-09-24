package ir.pajoohan.mce.service;

import ir.pajoohan.mce.dto.ColorDto;

import java.util.List;

public interface ColorService {

    List<ColorDto> getAll();

    ColorDto get(Long id);

    ColorDto save(ColorDto colorDto);

    ColorDto update(ColorDto colorDto);

    void delete(ColorDto colorDto);
}

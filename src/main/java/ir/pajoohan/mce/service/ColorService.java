package ir.pajoohan.mce.service;

import ir.pajoohan.mce.dto.ColorDto;
import org.springframework.data.domain.Page;

public interface ColorService {

    Page<ColorDto> getAll(Integer page, Integer size, String sort);

    ColorDto get(Long id);

    ColorDto save(ColorDto colorDto);

    ColorDto update(ColorDto colorDto);

    void delete(Long id);
}

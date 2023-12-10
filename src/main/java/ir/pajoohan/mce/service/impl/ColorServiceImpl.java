package ir.pajoohan.mce.service.impl;

import ir.pajoohan.mce.dto.ColorDto;
import ir.pajoohan.mce.dto.ColorMapper;
import ir.pajoohan.mce.entity.Color;
import ir.pajoohan.mce.repository.ColorRepository;
import ir.pajoohan.mce.service.ColorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ColorServiceImpl implements ColorService {

    ColorRepository colorRepository;

    /**
     * Setters
     */
    @Autowired
    public void setColorRepository(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * Methods
     */
    @Override
    public List<ColorDto> getAll() {
        List<Color> colorList = colorRepository.findAll();
        List<ColorDto> colorDtoList = new ArrayList<>();
        for (Color c : colorList) {
            colorDtoList.add(ColorMapper.INSTANCE.colorToColorDto(c));
        }
        return colorDtoList;
    }

    @Override
    public ColorDto get(Long id) {
        Color color = colorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return ColorMapper.INSTANCE.colorToColorDto(color);
    }

    @Override
    public ColorDto save(ColorDto colorDto) {
        Color color = ColorMapper.INSTANCE.colorDtoToColor(colorDto);
        color.setId(null);
        Color colorSaved = colorRepository.save(color);
        return ColorMapper.INSTANCE.colorToColorDto(colorSaved);
    }

    @Override
    public ColorDto update(ColorDto colorDto) {
        Optional<Color> optionalColor = colorRepository.findById(colorDto.getId());

        if (optionalColor.isEmpty()) {
            throw new EntityNotFoundException("COLOR with ID : '" + colorDto.getId() + "' not found.");
        }
        Color color = optionalColor.get();
        ColorMapper.INSTANCE.updateColorFromDto(colorDto, color);
        Color colorSaved = colorRepository.save(color);
        return ColorMapper.INSTANCE.colorToColorDto(colorSaved);
    }

    @Override
    public void delete(Long id) {
        Color color = colorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        colorRepository.delete(color);
    }
}

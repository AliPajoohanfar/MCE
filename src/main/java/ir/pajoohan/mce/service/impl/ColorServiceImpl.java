package ir.pajoohan.mce.service.impl;

import ir.pajoohan.mce.dto.ColorDto;
import ir.pajoohan.mce.dto.ColorMapper;
import ir.pajoohan.mce.entity.Color;
import ir.pajoohan.mce.repository.ColorRepository;
import ir.pajoohan.mce.service.ColorService;
import ir.pajoohan.mce.util.Messages;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ColorServiceImpl implements ColorService {

    ColorRepository colorRepository;

    @Autowired
    public void setColorRepository(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

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
        Color color = colorRepository.findById(id).orElseThrow(
                () -> new NoResultException(Messages.get("ex.noDataFound")));

        return ColorMapper.INSTANCE.colorToColorDto(color);
    }

    @Override
    public ColorDto save(ColorDto colorDto) {
        Color color = ColorMapper.INSTANCE.colorDtoToColor(colorDto);
        return ColorMapper.INSTANCE.colorToColorDto(colorRepository.save(color));
    }

    @Override
    public ColorDto update(ColorDto colorDto) {
        Color color = ColorMapper.INSTANCE.colorDtoToColor(colorDto);
        return ColorMapper.INSTANCE.colorToColorDto(colorRepository.save(color));
    }

    @Override
    public void delete(ColorDto colorDto) {
        Color color = ColorMapper.INSTANCE.colorDtoToColor(colorDto);
        colorRepository.delete(color);
    }
}

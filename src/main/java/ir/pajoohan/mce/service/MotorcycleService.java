package ir.pajoohan.mce.service;

import ir.pajoohan.mce.dto.MotorcycleDto;

import java.util.List;

public interface MotorcycleService {

    List<MotorcycleDto> getAll();

    MotorcycleDto get(Long id);

    MotorcycleDto save(MotorcycleDto motorcycleDto);

    MotorcycleDto update(MotorcycleDto motorcycleDto);

    void delete(Long id);
}

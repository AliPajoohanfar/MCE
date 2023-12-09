package ir.pajoohan.mce.service;

import ir.pajoohan.mce.dto.MotorcycleTypeDto;

import java.util.List;

public interface MotorcycleTypeService {

    List<MotorcycleTypeDto> getAll();

    MotorcycleTypeDto get(Long id);

    MotorcycleTypeDto save(MotorcycleTypeDto motorcycleTypeDto);

    MotorcycleTypeDto update(MotorcycleTypeDto motorcycleTypeDto);

    void delete(Long id);
}

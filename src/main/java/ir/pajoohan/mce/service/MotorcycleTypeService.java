package ir.pajoohan.mce.service;

import ir.pajoohan.mce.dto.MotorcycleTypeDto;
import org.springframework.data.domain.Page;

public interface MotorcycleTypeService {

    Page<MotorcycleTypeDto> getAll(Integer page, Integer size, String sort);

    MotorcycleTypeDto get(Long id);

    MotorcycleTypeDto save(MotorcycleTypeDto motorcycleTypeDto);

    MotorcycleTypeDto update(MotorcycleTypeDto motorcycleTypeDto);

    void delete(Long id);
}

package ir.pajoohan.mce.service;

import ir.pajoohan.mce.dto.MotorcycleDto;
import org.springframework.data.domain.Page;

public interface MotorcycleService {

    Page<MotorcycleDto> getAll(Integer page, Integer size, String sort);

    MotorcycleDto get(Long id);

    MotorcycleDto save(MotorcycleDto motorcycleDto);

    MotorcycleDto update(MotorcycleDto motorcycleDto);

    void delete(Long id);
}

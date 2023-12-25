package ir.pajoohan.mce.service;

import ir.pajoohan.mce.dto.EngineTypeDto;
import org.springframework.data.domain.Page;

public interface EngineTypeService {

    Page<EngineTypeDto> getAll(Integer page, Integer size, String sort);

    EngineTypeDto get(Long id);

    EngineTypeDto save(EngineTypeDto engineTypeDto);

    EngineTypeDto update(EngineTypeDto engineTypeDto);

    void delete(Long id);
}

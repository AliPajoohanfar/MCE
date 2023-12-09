package ir.pajoohan.mce.service;

import ir.pajoohan.mce.dto.EngineTypeDto;

import java.util.List;

public interface EngineTypeService {

    List<EngineTypeDto> getAll();

    EngineTypeDto get(Long id);

    EngineTypeDto save(EngineTypeDto engineTypeDto);

    EngineTypeDto update(EngineTypeDto engineTypeDto);

    void delete(Long id);
}

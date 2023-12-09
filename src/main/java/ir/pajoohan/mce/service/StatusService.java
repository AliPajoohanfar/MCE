package ir.pajoohan.mce.service;

import ir.pajoohan.mce.dto.StatusDto;

import java.util.List;

public interface StatusService {

    List<StatusDto> getAll();

    StatusDto get(Long id);

    StatusDto save(StatusDto stateDto);

    StatusDto update(StatusDto stateDto);

    void delete(Long id);
}

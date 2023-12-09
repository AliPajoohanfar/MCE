package ir.pajoohan.mce.service;

import ir.pajoohan.mce.dto.StatusDto;

import java.util.List;

public interface StatusService {

    List<StatusDto> getAll();

    StatusDto get(Long id);

    StatusDto save(StatusDto statusDto);

    StatusDto update(StatusDto statusDto);

    void delete(Long id);
}

package ir.pajoohan.mce.service;

import ir.pajoohan.mce.dto.StatusDto;
import org.springframework.data.domain.Page;

public interface StatusService {

    Page<StatusDto> getAll(Integer page, Integer size, String sort);

    StatusDto get(Long id);

    StatusDto save(StatusDto statusDto);

    StatusDto update(StatusDto statusDto);

    void delete(Long id);
}

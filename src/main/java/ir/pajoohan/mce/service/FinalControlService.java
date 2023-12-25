package ir.pajoohan.mce.service;

import ir.pajoohan.mce.dto.FinalControlDto;
import org.springframework.data.domain.Page;

public interface FinalControlService {

    Page<FinalControlDto> getAll(Integer page, Integer size, String sort);

    FinalControlDto get(Long id);

    FinalControlDto save(FinalControlDto finalControlDto);

    FinalControlDto update(FinalControlDto finalControlDto);

    void delete(Long id);
}

package ir.pajoohan.mce.service;

import ir.pajoohan.mce.dto.FinalControlDto;

import java.util.List;

public interface FinalControlService {

    List<FinalControlDto> getAll();

    FinalControlDto get(Long id);

    FinalControlDto save(FinalControlDto finalControlDto);

    FinalControlDto update(FinalControlDto finalControlDto);

    void delete(Long id);
}

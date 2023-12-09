package ir.pajoohan.mce.service;

import ir.pajoohan.mce.dto.AftersalesServiceDto;

import java.util.List;

public interface AftersalesServiceService {

    List<AftersalesServiceDto> getAll();

    AftersalesServiceDto get(Long id);

    AftersalesServiceDto save(AftersalesServiceDto aftersalesServiceDto);

    AftersalesServiceDto update(AftersalesServiceDto aftersalesServiceDto);

    void delete(Long id);
}

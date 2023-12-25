package ir.pajoohan.mce.service;

import ir.pajoohan.mce.dto.AftersalesServiceDto;
import org.springframework.data.domain.Page;

public interface AftersalesServiceService {

    Page<AftersalesServiceDto> getAll(Integer page, Integer size, String sort);

    AftersalesServiceDto get(Long id);

    AftersalesServiceDto save(AftersalesServiceDto aftersalesServiceDto);

    AftersalesServiceDto update(AftersalesServiceDto aftersalesServiceDto);

    void delete(Long id);
}

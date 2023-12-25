package ir.pajoohan.mce.service;

import ir.pajoohan.mce.dto.QualityControlDto;
import org.springframework.data.domain.Page;

public interface QualityControlService {

    Page<QualityControlDto> getAll(Integer page, Integer size, String sort);

    QualityControlDto get(Long id);

    QualityControlDto save(QualityControlDto qualityControlDto);

    QualityControlDto update(QualityControlDto qualityControlDto);

    void delete(Long id);
}

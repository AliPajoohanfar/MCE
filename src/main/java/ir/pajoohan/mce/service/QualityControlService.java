package ir.pajoohan.mce.service;

import ir.pajoohan.mce.dto.QualityControlDto;

import java.util.List;

public interface QualityControlService {

    List<QualityControlDto> getAll();

    QualityControlDto get(Long id);

    QualityControlDto save(QualityControlDto qualityControlDto);

    QualityControlDto update(QualityControlDto qualityControlDto);

    void delete(Long id);
}

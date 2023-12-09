package ir.pajoohan.mce.service;

import ir.pajoohan.mce.dto.WarehouseInputDto;

import java.util.List;

public interface WarehouseInputService {

    List<WarehouseInputDto> getAll();

    WarehouseInputDto get(Long id);

    WarehouseInputDto save(WarehouseInputDto warehouseInputDto);

    WarehouseInputDto update(WarehouseInputDto warehouseInputDto);

    void delete(Long id);
}

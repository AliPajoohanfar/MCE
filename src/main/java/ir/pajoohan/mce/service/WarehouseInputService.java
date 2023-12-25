package ir.pajoohan.mce.service;

import ir.pajoohan.mce.dto.WarehouseInputDto;
import org.springframework.data.domain.Page;

public interface WarehouseInputService {

    Page<WarehouseInputDto> getAll(Integer page, Integer size, String sort);

    WarehouseInputDto get(Long id);

    WarehouseInputDto save(WarehouseInputDto warehouseInputDto);

    WarehouseInputDto update(WarehouseInputDto warehouseInputDto);

    void delete(Long id);
}

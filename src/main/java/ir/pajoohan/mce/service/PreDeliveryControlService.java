package ir.pajoohan.mce.service;

import ir.pajoohan.mce.dto.PreDeliveryControlDto;

import java.util.List;

public interface PreDeliveryControlService {

    List<PreDeliveryControlDto> getAll();

    PreDeliveryControlDto get(Long id);

    PreDeliveryControlDto save(PreDeliveryControlDto preDeliveryControlDto);

    PreDeliveryControlDto update(PreDeliveryControlDto preDeliveryControlDto);

    void delete(Long id);
}

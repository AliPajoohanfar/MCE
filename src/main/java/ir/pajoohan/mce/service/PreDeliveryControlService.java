package ir.pajoohan.mce.service;

import ir.pajoohan.mce.dto.PreDeliveryControlDto;
import org.springframework.data.domain.Page;

public interface PreDeliveryControlService {

    Page<PreDeliveryControlDto> getAll(Integer page, Integer size, String sort);

    PreDeliveryControlDto get(Long id);

    PreDeliveryControlDto save(PreDeliveryControlDto preDeliveryControlDto);

    PreDeliveryControlDto update(PreDeliveryControlDto preDeliveryControlDto);

    void delete(Long id);
}

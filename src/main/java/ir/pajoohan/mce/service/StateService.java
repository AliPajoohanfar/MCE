package ir.pajoohan.mce.service;

import ir.pajoohan.mce.dto.StateDto;
import org.springframework.data.domain.Page;

public interface StateService {

    Page<StateDto> getAll(Integer page, Integer size, String sort);

    StateDto get(Long id);

    StateDto save(StateDto stateDto);

    StateDto update(StateDto stateDto);

    void delete(Long id);
}

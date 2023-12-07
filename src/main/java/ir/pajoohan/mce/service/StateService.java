package ir.pajoohan.mce.service;

import ir.pajoohan.mce.dto.StateDto;

import java.util.List;

public interface StateService {

    List<StateDto> getAll();

    StateDto get(Long id);

    StateDto save(StateDto stateDto);

    StateDto update(StateDto stateDto);

    void delete(Long id);
}

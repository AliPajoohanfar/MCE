package ir.pajoohan.mce.service;

import ir.pajoohan.mce.entity.State;

import java.util.List;
import java.util.Optional;

public interface StateService {

    List<State> getAll();

    Optional<State> get(Long Id);

    State save(State state);

    State update(State state);

    void delete(State state);
}

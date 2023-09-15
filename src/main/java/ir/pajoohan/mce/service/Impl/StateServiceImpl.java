package ir.pajoohan.mce.service.Impl;

import ir.pajoohan.mce.entity.State;
import ir.pajoohan.mce.repository.StateRepository;
import ir.pajoohan.mce.service.StateService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StateServiceImpl implements StateService {

    StateRepository stateRepository;

    @Autowired
    public void setStateRepository(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public List<State> getAll() {
        return stateRepository.findAll();
    }

    @Override
    public Optional<State> get(Long id) {
        return stateRepository.findById(id);
    }

    @Override
    public State save(State state) {
        return stateRepository.save(state);
    }

    @Override
    public State update(State state) {
        return stateRepository.save(state);
    }

    @Override
    public void delete(State state) {
        stateRepository.delete(state);
    }
}

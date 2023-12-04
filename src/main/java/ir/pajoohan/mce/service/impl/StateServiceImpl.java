package ir.pajoohan.mce.service.impl;

import ir.pajoohan.mce.dto.StateDto;
import ir.pajoohan.mce.dto.StateMapper;
import ir.pajoohan.mce.entity.State;
import ir.pajoohan.mce.repository.StateRepository;
import ir.pajoohan.mce.service.StateService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StateServiceImpl implements StateService {

    StateRepository stateRepository;

    /**
     * Setters
     */
    @Autowired
    public void setStateRepository(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * Methods
     */
    @Override
    public List<StateDto> getAll() {
        List<State> stateList = stateRepository.findAll();
        List<StateDto> stateDtoList = new ArrayList<>();
        for (State s : stateList) {
            stateDtoList.add(StateMapper.INSTANCE.stateToStateDto(s));
        }
        return stateDtoList;
    }

    @Override
    public StateDto get(Long id) {
        State state = stateRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return StateMapper.INSTANCE.stateToStateDto(state);
    }

    @Override
    public StateDto save(StateDto stateDto) {
        State state = StateMapper.INSTANCE.stateDtoToState(stateDto);
        return StateMapper.INSTANCE.stateToStateDto(stateRepository.save(state));
    }

    @Override
    public StateDto update(StateDto stateDto) {
        State state = StateMapper.INSTANCE.stateDtoToState(stateDto);
        return StateMapper.INSTANCE.stateToStateDto(stateRepository.save(state));
    }

    @Override
    public void delete(StateDto stateDto) {
        State state = StateMapper.INSTANCE.stateDtoToState(stateDto);
        stateRepository.delete(state);
    }
}

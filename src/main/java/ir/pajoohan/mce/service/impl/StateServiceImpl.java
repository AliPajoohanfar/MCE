package ir.pajoohan.mce.service.impl;

import ir.pajoohan.mce.dto.StateDto;
import ir.pajoohan.mce.dto.StateMapper;
import ir.pajoohan.mce.entity.State;
import ir.pajoohan.mce.repository.StateRepository;
import ir.pajoohan.mce.service.StateService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Page<StateDto> getAll(Integer page, Integer size, String sort) {

        Pageable pageable = PageRequest.of(page, size).withSort(Sort.Direction.ASC, sort);
        Page<State> statePage = stateRepository.findAll(pageable);
        List<State> stateList = statePage.stream().toList();

        List<StateDto> stateDtoList = new ArrayList<>();
        for (State s : stateList) {
            stateDtoList.add(StateMapper.INSTANCE.stateToStateDto(s));
        }
        Page<StateDto> stateDtoPage = new PageImpl<>(stateDtoList, pageable, statePage.getTotalElements());

        return stateDtoPage;
    }

    @Override
    public StateDto get(Long id) {
        State state = stateRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return StateMapper.INSTANCE.stateToStateDto(state);
    }

    @Override
    public StateDto save(StateDto stateDto) {
        State state = StateMapper.INSTANCE.stateDtoToState(stateDto);
        state.setId(null);
        State stateSaved = stateRepository.save(state);
        return StateMapper.INSTANCE.stateToStateDto(stateSaved);
    }

    @Override
    public StateDto update(StateDto stateDto) {
        Optional<State> optionalState = stateRepository.findById(stateDto.getId());
        if (optionalState.isEmpty()) {
            throw new EntityNotFoundException("STATE with ID : '" + stateDto.getId() + "' not found.");
        }

        State state = optionalState.get();
        StateMapper.INSTANCE.updateStateFromDto(stateDto, state);
        State stateSaved = stateRepository.save(state);
        return StateMapper.INSTANCE.stateToStateDto(stateSaved);
    }

    @Override
    public void delete(Long id) {
        State state = stateRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        stateRepository.delete(state);
    }
}

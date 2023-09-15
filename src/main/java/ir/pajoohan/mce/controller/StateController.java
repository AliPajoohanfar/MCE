package ir.pajoohan.mce.controller;

import ir.pajoohan.mce.confiuration.Messages;
import ir.pajoohan.mce.entity.State;
import ir.pajoohan.mce.service.Impl.StateServiceImpl;
import ir.pajoohan.mce.service.StateService;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/state")
public class StateController {

    StateService stateService;

    @Autowired
    public void setStateService(StateServiceImpl stateServiceImpl) {
        this.stateService = stateServiceImpl;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<State>> getAll() {
        return ResponseEntity.ok().body(stateService.getAll());
    }

    @GetMapping("/{stateId}")
    @ResponseBody
    public ResponseEntity<State> get(@PathVariable("stateId") Long stateId) {
        return ResponseEntity.ok().body(
                stateService.get(stateId).orElseThrow(
                        () -> new NoResultException(Messages.get("ex.noDataFound"))));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<State> insert(@RequestBody State state) {
        return ResponseEntity.ok().body(stateService.save(state));
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<State> update(@RequestBody State state) {
        return ResponseEntity.ok().body(stateService.update(state));
    }

    @DeleteMapping("/{stateId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("stateId") Long stateId) {
        Optional<State> optionalState = stateService.get(stateId);
        if (optionalState.isPresent()) {
            stateService.delete(optionalState.get());
        } else
            throw new NoResultException(Messages.get("ex.objectNotFoundToDelete"));
    }

}

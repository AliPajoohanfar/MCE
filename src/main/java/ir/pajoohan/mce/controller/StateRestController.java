package ir.pajoohan.mce.controller;

import ir.pajoohan.mce.dto.StateDto;
import ir.pajoohan.mce.service.StateService;
import ir.pajoohan.mce.service.impl.StateServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/state")
@Validated
public class StateRestController {

    StateService stateService;

    @Autowired
    public void setStateService(StateServiceImpl stateServiceImpl) {
        this.stateService = stateServiceImpl;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<StateDto>> getAll() {
        return ResponseEntity.ok().body(stateService.getAll());
    }

    @GetMapping("/{stateId}")
    @ResponseBody
    public ResponseEntity<StateDto> get(@PathVariable("stateId") Long stateId) {
        return ResponseEntity.ok().body(
                stateService.get(stateId));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<StateDto> insert(@RequestBody @Valid StateDto stateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stateService.save(stateDto));
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<StateDto> update(@RequestBody @Valid StateDto stateDto) {
        return ResponseEntity.ok().body(stateService.update(stateDto));
    }

    @DeleteMapping("/{stateId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("stateId") Long stateId) {
        StateDto stateDto = stateService.get(stateId);
        stateService.delete(stateDto);
    }

}

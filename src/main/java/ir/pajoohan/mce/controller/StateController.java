package ir.pajoohan.mce.controller;

import ir.pajoohan.mce.Dto.StateDto;
import ir.pajoohan.mce.service.Impl.StateServiceImpl;
import ir.pajoohan.mce.service.StateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.ok().body(stateService.save(stateDto));
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

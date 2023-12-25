package ir.pajoohan.mce.controller;

import ir.pajoohan.mce.dto.StateDto;
import ir.pajoohan.mce.service.StateService;
import ir.pajoohan.mce.service.impl.StateServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/v1/state")
@Validated
public class StateRestController {

    StateService stateService;

    /**
     * Setters
     */
    @Autowired
    public void setStateService(StateServiceImpl stateServiceImpl) {
        this.stateService = stateServiceImpl;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * Methods
     */
    @GetMapping
    @ResponseBody
    public ResponseEntity<Page<StateDto>> getAll(@RequestParam("page") Optional<Integer> page,
                                                 @RequestParam("size") Optional<Integer> size,
                                                 @RequestParam("sort") Optional<String> sort) {

        return ResponseEntity.ok().body(stateService.getAll(page.orElse(0), size.orElse(10), sort.orElse("id")));
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
        stateService.delete(stateId);
    }

}

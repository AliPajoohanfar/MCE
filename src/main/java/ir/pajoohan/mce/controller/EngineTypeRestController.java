package ir.pajoohan.mce.controller;

import io.swagger.v3.oas.annotations.Operation;
import ir.pajoohan.mce.dto.EngineTypeDto;
import ir.pajoohan.mce.service.EngineTypeService;
import ir.pajoohan.mce.service.impl.EngineTypeServiceImpl;
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
@RequestMapping("/v1/enginetype")
@Validated
public class EngineTypeRestController {

    EngineTypeService engineTypeService;

    /**
     * Setters
     */
    @Autowired
    public void setEngineTypeService(EngineTypeServiceImpl engineTypeServiceImpl) {
        this.engineTypeService = engineTypeServiceImpl;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * Methods
     */
    @GetMapping
    @ResponseBody
    @Operation(summary = "Get all engine types by pagination and sort options.")
    public ResponseEntity<Page<EngineTypeDto>> getAll(@RequestParam("page") Optional<Integer> page,
                                                      @RequestParam("size") Optional<Integer> size,
                                                      @RequestParam("sort") Optional<String> sort) {

        return ResponseEntity.ok().body(engineTypeService.getAll(page.orElse(0), size.orElse(10), sort.orElse("id")));
    }

    @GetMapping("/{engineTypeId}")
    @ResponseBody
    @Operation(summary = "Get a engine type attachment by id.")
    public ResponseEntity<EngineTypeDto> get(@PathVariable("engineTypeId") Long engineTypeId) {
        return ResponseEntity.ok().body(
                engineTypeService.get(engineTypeId));
    }

    @PostMapping
    @ResponseBody
    @Operation(summary = "Add a new engine type.")
    public ResponseEntity<EngineTypeDto> insert(@RequestBody @Valid EngineTypeDto engineTypeDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(engineTypeService.save(engineTypeDto));
    }

    @PutMapping
    @ResponseBody
    @Operation(summary = "Update a specific engine type by id.")
    public ResponseEntity<EngineTypeDto> update(@RequestBody @Valid EngineTypeDto engineTypeDto) {
        return ResponseEntity.ok().body(engineTypeService.update(engineTypeDto));
    }

    @DeleteMapping("/{engineTypeId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Soft delete a specific engine type by id.")
    public void delete(@PathVariable("engineTypeId") Long engineTypeId) {
        engineTypeService.delete(engineTypeId);
    }

}

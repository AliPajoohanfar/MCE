package ir.pajoohan.mce.controller;

import io.swagger.v3.oas.annotations.Operation;
import ir.pajoohan.mce.dto.MotorcycleTypeDto;
import ir.pajoohan.mce.service.MotorcycleTypeService;
import ir.pajoohan.mce.service.impl.MotorcycleTypeServiceImpl;
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
@RequestMapping("/v1/motorcycletype")
@Validated
public class MotorcycleTypeRestController {

    MotorcycleTypeService motorcycleTypeService;

    /**
     * Setters
     */
    @Autowired
    public void setMotorcycleTypeService(MotorcycleTypeServiceImpl motorcycleTypeServiceImpl) {
        this.motorcycleTypeService = motorcycleTypeServiceImpl;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * Methods
     */
    @GetMapping
    @ResponseBody
    @Operation(summary = "Get all motorcycle types by pagination and sort options.")
    public ResponseEntity<Page<MotorcycleTypeDto>> getAll(@RequestParam("page") Optional<Integer> page,
                                                          @RequestParam("size") Optional<Integer> size,
                                                          @RequestParam("sort") Optional<String> sort) {

        return ResponseEntity.ok().body(motorcycleTypeService.getAll(page.orElse(0), size.orElse(10), sort.orElse("id")));
    }

    @GetMapping("/{motorcycleTypeId}")
    @ResponseBody
    @Operation(summary = "Get a specific motorcycle type by id.")
    public ResponseEntity<MotorcycleTypeDto> get(@PathVariable("motorcycleTypeId") Long motorcycleTypeId) {
        return ResponseEntity.ok().body(
                motorcycleTypeService.get(motorcycleTypeId));
    }

    @PostMapping
    @ResponseBody
    @Operation(summary = "Add a new motorcycle type.")
    public ResponseEntity<MotorcycleTypeDto> insert(@RequestBody @Valid MotorcycleTypeDto motorcycleTypeDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(motorcycleTypeService.save(motorcycleTypeDto));
    }

    @PutMapping
    @ResponseBody
    @Operation(summary = "Update a specific motorcycle type by id.")
    public ResponseEntity<MotorcycleTypeDto> update(@RequestBody @Valid MotorcycleTypeDto motorcycleTypeDto) {
        return ResponseEntity.ok().body(motorcycleTypeService.update(motorcycleTypeDto));
    }

    @DeleteMapping("/{motorcycleTypeId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Soft delete a specific motorcycle type by id.")
    public void delete(@PathVariable("motorcycleTypeId") Long motorcycleTypeId) {
        motorcycleTypeService.delete(motorcycleTypeId);
    }

}

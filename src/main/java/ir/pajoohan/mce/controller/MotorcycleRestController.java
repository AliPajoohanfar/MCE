package ir.pajoohan.mce.controller;

import io.swagger.v3.oas.annotations.Operation;
import ir.pajoohan.mce.dto.MotorcycleDto;
import ir.pajoohan.mce.service.MotorcycleService;
import ir.pajoohan.mce.service.impl.MotorcycleServiceImpl;
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
@RequestMapping("/v1/motorcycle")
@Validated
public class MotorcycleRestController {

    MotorcycleService motorcycleService;

    /**
     * Setters
     */
    @Autowired
    public void setMotorcycleService(MotorcycleServiceImpl motorcycleServiceImpl) {
        this.motorcycleService = motorcycleServiceImpl;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * Methods
     */
    @GetMapping
    @ResponseBody
    @Operation(summary = "Get all motorcycles by pagination and sort options.")
    public ResponseEntity<Page<MotorcycleDto>> getAll(@RequestParam("page") Optional<Integer> page,
                                                      @RequestParam("size") Optional<Integer> size,
                                                      @RequestParam("sort") Optional<String> sort) {

        return ResponseEntity.ok().body(motorcycleService.getAll(page.orElse(0), size.orElse(10), sort.orElse("id")));
    }

    @GetMapping("/{motorcycleId}")
    @ResponseBody
    @Operation(summary = "Get a specific motorcycle by id.")
    public ResponseEntity<MotorcycleDto> get(@PathVariable("motorcycleId") Long motorcycleId) {
        return ResponseEntity.ok().body(
                motorcycleService.get(motorcycleId));
    }

    @PostMapping
    @ResponseBody
    @Operation(summary = "Add a new motorcycle.")
    public ResponseEntity<MotorcycleDto> insert(@RequestBody @Valid MotorcycleDto motorcycleDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(motorcycleService.save(motorcycleDto));
    }

    @PutMapping
    @ResponseBody
    @Operation(summary = "Update a specific motorcycle by id.")
    public ResponseEntity<MotorcycleDto> update(@RequestBody @Valid MotorcycleDto motorcycleDto) {
        return ResponseEntity.ok().body(motorcycleService.update(motorcycleDto));
    }

    @DeleteMapping("/{motorcycleId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Soft delete a specific motorcycle by id.")
    public void delete(@PathVariable("motorcycleId") Long motorcycleId) {
        motorcycleService.delete(motorcycleId);
    }

}

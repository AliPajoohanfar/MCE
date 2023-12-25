package ir.pajoohan.mce.controller;

import io.swagger.v3.oas.annotations.Operation;
import ir.pajoohan.mce.dto.WarehouseInputDto;
import ir.pajoohan.mce.service.WarehouseInputService;
import ir.pajoohan.mce.service.impl.WarehouseInputServiceImpl;
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
@RequestMapping("/v1/warehouseinput")
@Validated
public class WarehouseInputRestController {

    WarehouseInputService warehouseInputService;

    /**
     * Setters
     */
    @Autowired
    public void setWarehouseInputService(WarehouseInputServiceImpl warehouseInputServiceImpl) {
        this.warehouseInputService = warehouseInputServiceImpl;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * Methods
     */
    @GetMapping
    @ResponseBody
    @Operation(summary = "Get all warehouse inputs by pagination and sort options.")
    public ResponseEntity<Page<WarehouseInputDto>> getAll(@RequestParam("page") Optional<Integer> page,
                                                          @RequestParam("size") Optional<Integer> size,
                                                          @RequestParam("sort") Optional<String> sort) {

        return ResponseEntity.ok().body(warehouseInputService.getAll(page.orElse(0), size.orElse(10), sort.orElse("id")));
    }

    @GetMapping("/{warehouseInputId}")
    @ResponseBody
    @Operation(summary = "Get a specific warehouse input by id.")
    public ResponseEntity<WarehouseInputDto> get(@PathVariable("warehouseInputId") Long warehouseInputId) {
        return ResponseEntity.ok().body(
                warehouseInputService.get(warehouseInputId));
    }

    @PostMapping
    @ResponseBody
    @Operation(summary = "Add a new warehouse input.")
    public ResponseEntity<WarehouseInputDto> insert(@RequestBody @Valid WarehouseInputDto warehouseInputDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(warehouseInputService.save(warehouseInputDto));
    }

    @PutMapping
    @ResponseBody
    @Operation(summary = "Update a specific warehouse input by id.")
    public ResponseEntity<WarehouseInputDto> update(@RequestBody @Valid WarehouseInputDto warehouseInputDto) {
        return ResponseEntity.ok().body(warehouseInputService.update(warehouseInputDto));
    }

    @DeleteMapping("/{warehouseInputId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Soft delete a specific warehouse input by id.")
    public void delete(@PathVariable("warehouseInputId") Long warehouseInputId) {
        warehouseInputService.delete(warehouseInputId);
    }

}
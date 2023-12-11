package ir.pajoohan.mce.controller;

import ir.pajoohan.mce.dto.WarehouseInputDto;
import ir.pajoohan.mce.service.WarehouseInputService;
import ir.pajoohan.mce.service.impl.WarehouseInputServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResponseEntity<List<WarehouseInputDto>> getAll() {
        return ResponseEntity.ok().body(warehouseInputService.getAll());
    }

    @GetMapping("/{warehouseInputId}")
    @ResponseBody
    public ResponseEntity<WarehouseInputDto> get(@PathVariable("warehouseInputId") Long warehouseInputId) {
        return ResponseEntity.ok().body(
                warehouseInputService.get(warehouseInputId));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<WarehouseInputDto> insert(@RequestBody @Valid WarehouseInputDto warehouseInputDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(warehouseInputService.save(warehouseInputDto));
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<WarehouseInputDto> update(@RequestBody @Valid WarehouseInputDto warehouseInputDto) {
        return ResponseEntity.ok().body(warehouseInputService.update(warehouseInputDto));
    }

    @DeleteMapping("/{warehouseInputId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("warehouseInputId") Long warehouseInputId) {
        warehouseInputService.delete(warehouseInputId);
    }

}
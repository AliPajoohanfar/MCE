package ir.pajoohan.mce.controller;


import ir.pajoohan.mce.dto.MotorcycleTypeDto;
import ir.pajoohan.mce.service.MotorcycleTypeService;
import ir.pajoohan.mce.service.impl.MotorcycleTypeServiceImpl;
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
    public ResponseEntity<List<MotorcycleTypeDto>> getAll() {
        return ResponseEntity.ok().body(motorcycleTypeService.getAll());
    }

    @GetMapping("/{motorcycleTypeId}")
    @ResponseBody
    public ResponseEntity<MotorcycleTypeDto> get(@PathVariable("motorcycleTypeId") Long motorcycleTypeId) {
        return ResponseEntity.ok().body(
                motorcycleTypeService.get(motorcycleTypeId));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<MotorcycleTypeDto> insert(@RequestBody @Valid MotorcycleTypeDto motorcycleTypeDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(motorcycleTypeService.save(motorcycleTypeDto));
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<MotorcycleTypeDto> update(@RequestBody @Valid MotorcycleTypeDto motorcycleTypeDto) {
        return ResponseEntity.ok().body(motorcycleTypeService.update(motorcycleTypeDto));
    }

    @DeleteMapping("/{motorcycleTypeId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("motorcycleTypeId") Long motorcycleTypeId) {
        motorcycleTypeService.delete(motorcycleTypeId);
    }

}

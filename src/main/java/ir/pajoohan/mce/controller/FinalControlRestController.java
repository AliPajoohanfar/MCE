package ir.pajoohan.mce.controller;

import ir.pajoohan.mce.dto.FinalControlDto;
import ir.pajoohan.mce.service.FinalControlService;
import ir.pajoohan.mce.service.impl.FinalControlServiceImpl;
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
@RequestMapping("/v1/finalcontrol")
@Validated
public class FinalControlRestController {

    FinalControlService finalControlService;

    /**
     * Setters
     */
    @Autowired
    public void setFinalControlService(FinalControlServiceImpl finalControlServiceImpl) {
        this.finalControlService = finalControlServiceImpl;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * Methods
     */
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<FinalControlDto>> getAll() {
        return ResponseEntity.ok().body(finalControlService.getAll());
    }

    @GetMapping("/{finalControlId}")
    @ResponseBody
    public ResponseEntity<FinalControlDto> get(@PathVariable("finalControlId") Long finalControlId) {
        return ResponseEntity.ok().body(
                finalControlService.get(finalControlId));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<FinalControlDto> insert(@RequestBody @Valid FinalControlDto finalControlDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(finalControlService.save(finalControlDto));
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<FinalControlDto> update(@RequestBody @Valid FinalControlDto finalControlDto) {
        return ResponseEntity.ok().body(finalControlService.update(finalControlDto));
    }

    @DeleteMapping("/{finalControlId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("finalControlId") Long finalControlId) {
        finalControlService.delete(finalControlId);
    }

}

package ir.pajoohan.mce.controller;

import io.swagger.v3.oas.annotations.Operation;
import ir.pajoohan.mce.dto.QualityControlDto;
import ir.pajoohan.mce.service.QualityControlService;
import ir.pajoohan.mce.service.impl.QualityControlServiceImpl;
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
@RequestMapping("/v1/qualitycontrol")
@Validated
public class QualityControlRestController {

    QualityControlService qualityControlService;

    /**
     * Setters
     */
    @Autowired
    public void setQualityControlService(QualityControlServiceImpl qualityControlServiceImpl) {
        this.qualityControlService = qualityControlServiceImpl;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * Methods
     */
    @GetMapping
    @ResponseBody
    @Operation(summary = "Get all quality controls by pagination and sort options.")
    public ResponseEntity<Page<QualityControlDto>> getAll(@RequestParam("page") Optional<Integer> page,
                                                          @RequestParam("size") Optional<Integer> size,
                                                          @RequestParam("sort") Optional<String> sort) {

        return ResponseEntity.ok().body(qualityControlService.getAll(page.orElse(0), size.orElse(10), sort.orElse("id")));
    }

    @GetMapping("/{qualityControlId}")
    @ResponseBody
    @Operation(summary = "Get a specific quality control by id.")
    public ResponseEntity<QualityControlDto> get(@PathVariable("qualityControlId") Long qualityControlId) {
        return ResponseEntity.ok().body(
                qualityControlService.get(qualityControlId));
    }

    @PostMapping
    @ResponseBody
    @Operation(summary = "Add a new quality control.")
    public ResponseEntity<QualityControlDto> insert(@RequestBody @Valid QualityControlDto qualityControlDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(qualityControlService.save(qualityControlDto));
    }

    @PutMapping
    @ResponseBody
    @Operation(summary = "Update a specific quality control by id.")
    public ResponseEntity<QualityControlDto> update(@RequestBody @Valid QualityControlDto qualityControlDto) {
        return ResponseEntity.ok().body(qualityControlService.update(qualityControlDto));
    }

    @DeleteMapping("/{qualityControlId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Soft delete a specific quality control by id.")
    public void delete(@PathVariable("qualityControlId") Long qualityControlId) {
        qualityControlService.delete(qualityControlId);
    }

}

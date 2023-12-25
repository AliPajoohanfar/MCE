package ir.pajoohan.mce.controller;

import io.swagger.v3.oas.annotations.Operation;
import ir.pajoohan.mce.dto.AftersalesServiceDto;
import ir.pajoohan.mce.service.AftersalesServiceService;
import ir.pajoohan.mce.service.impl.AftersalesServiceServiceImpl;
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
@RequestMapping("/v1/aftersalesservice")
@Validated
public class AftersalesServiceRestController {

    AftersalesServiceService aftersalesServiceService;

    /**
     * Setters
     */
    @Autowired
    public void setAftersalesServiceService(AftersalesServiceServiceImpl aftersalesServiceServiceImpl) {
        this.aftersalesServiceService = aftersalesServiceServiceImpl;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * Methods
     */
    @GetMapping
    @ResponseBody
    @Operation(summary = "Get all after sale services by pagination and sort options.")
    public ResponseEntity<Page<AftersalesServiceDto>> getAll(@RequestParam("page") Optional<Integer> page,
                                                             @RequestParam("size") Optional<Integer> size,
                                                             @RequestParam("sort") Optional<String> sort) {

        return ResponseEntity.ok().body(aftersalesServiceService.getAll(page.orElse(0), size.orElse(10), sort.orElse("id")));
    }

    @GetMapping("/{aftersalesServiceId}")
    @ResponseBody
    @Operation(summary = "Get a specific after sale service by id.")
    public ResponseEntity<AftersalesServiceDto> get(@PathVariable("aftersalesServiceId") Long aftersalesServiceId) {
        return ResponseEntity.ok().body(
                aftersalesServiceService.get(aftersalesServiceId));
    }

    @PostMapping
    @ResponseBody
    @Operation(summary = "Add a new after sale service.")
    public ResponseEntity<AftersalesServiceDto> insert(@RequestBody @Valid AftersalesServiceDto aftersalesServiceDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(aftersalesServiceService.save(aftersalesServiceDto));
    }

    @PutMapping
    @ResponseBody
    @Operation(summary = "Update a specific after sale service by id.")
    public ResponseEntity<AftersalesServiceDto> update(@RequestBody @Valid AftersalesServiceDto aftersalesServiceDto) {
        return ResponseEntity.ok().body(aftersalesServiceService.update(aftersalesServiceDto));
    }

    @DeleteMapping("/{aftersalesServiceId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Soft delete a specific after sale service by id.")
    public void delete(@PathVariable("aftersalesServiceId") Long aftersalesServiceId) {
        aftersalesServiceService.delete(aftersalesServiceId);
    }

}

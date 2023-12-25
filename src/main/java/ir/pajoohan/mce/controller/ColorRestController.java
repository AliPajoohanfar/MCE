package ir.pajoohan.mce.controller;

import io.swagger.v3.oas.annotations.Operation;
import ir.pajoohan.mce.dto.ColorDto;
import ir.pajoohan.mce.service.ColorService;
import ir.pajoohan.mce.service.impl.ColorServiceImpl;
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
@RequestMapping("/v1/color")
@Validated
public class ColorRestController {

    ColorService colorService;

    @Autowired
    public void setColorService(ColorServiceImpl colorServiceImpl) {
        this.colorService = colorServiceImpl;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    @GetMapping
    @ResponseBody
    @Operation(summary = "Get all colors by pagination and sort options.")
    public ResponseEntity<Page<ColorDto>> getAll(@RequestParam("page") Optional<Integer> page,
                                                 @RequestParam("size") Optional<Integer> size,
                                                 @RequestParam("sort") Optional<String> sort) {

        return ResponseEntity.ok().body(colorService.getAll(page.orElse(0), size.orElse(10), sort.orElse("id")));
    }

    @GetMapping("/{colorId}")
    @ResponseBody
    @Operation(summary = "Get a specific color by id.")
    public ResponseEntity<ColorDto> get(@PathVariable("colorId") Long colorId) {
        return ResponseEntity.ok().body(colorService.get(colorId));
    }

    @PostMapping
    @ResponseBody
    @Operation(summary = "Add a new color.")
    public ResponseEntity<ColorDto> insert(@RequestBody @Valid ColorDto colorDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(colorService.save(colorDto));
    }

    @PutMapping
    @ResponseBody
    @Operation(summary = "Update a specific color by id.")
    public ResponseEntity<ColorDto> update(@RequestBody @Valid ColorDto colorDto) {
        return ResponseEntity.ok().body(colorService.update(colorDto));
    }

    @DeleteMapping("/{colorId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Soft delete a specific color by id.")
    public void delete(@PathVariable("colorId") Long colorId) {
        colorService.delete(colorId);
    }

}

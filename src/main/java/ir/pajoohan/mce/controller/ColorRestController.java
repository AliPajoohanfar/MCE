package ir.pajoohan.mce.controller;

import ir.pajoohan.mce.dto.ColorDto;
import ir.pajoohan.mce.service.ColorService;
import ir.pajoohan.mce.service.impl.ColorServiceImpl;
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
    public ResponseEntity<List<ColorDto>> getAll() {
        return ResponseEntity.ok().body(colorService.getAll());
    }

    @GetMapping("/{colorId}")
    @ResponseBody
    public ResponseEntity<ColorDto> get(@PathVariable("colorId") Long colorId) {
        return ResponseEntity.ok().body(colorService.get(colorId));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ColorDto> insert(@RequestBody @Valid ColorDto colorDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(colorService.save(colorDto));
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<ColorDto> update(@RequestBody @Valid ColorDto colorDto) {
        return ResponseEntity.ok().body(colorService.update(colorDto));
    }

    @DeleteMapping("/{colorId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("colorId") Long colorId) {
        colorService.delete(colorId);
    }

}

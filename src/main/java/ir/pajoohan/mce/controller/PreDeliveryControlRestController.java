package ir.pajoohan.mce.controller;

import ir.pajoohan.mce.dto.PreDeliveryControlDto;
import ir.pajoohan.mce.service.PreDeliveryControlService;
import ir.pajoohan.mce.service.impl.PreDeliveryControlServiceImpl;
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
@RequestMapping("/v1/predeliverycontrol")
@Validated
public class PreDeliveryControlRestController {

    PreDeliveryControlService preDeliveryControlService;

    /**
     * Setters
     */
    @Autowired
    public void setPreDeliveryControlService(PreDeliveryControlServiceImpl preDeliveryControlServiceImpl) {
        this.preDeliveryControlService = preDeliveryControlServiceImpl;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * Methods
     */
    @GetMapping
    @ResponseBody
    public ResponseEntity<Page<PreDeliveryControlDto>> getAll(@RequestParam("page") Optional<Integer> page,
                                                              @RequestParam("size") Optional<Integer> size,
                                                              @RequestParam("sort") Optional<String> sort) {

        return ResponseEntity.ok().body(preDeliveryControlService.getAll(page.orElse(0), size.orElse(10), sort.orElse("id")));
    }

    @GetMapping("/{preDeliveryControlId}")
    @ResponseBody
    public ResponseEntity<PreDeliveryControlDto> get(@PathVariable("preDeliveryControlId") Long preDeliveryControlId) {
        return ResponseEntity.ok().body(
                preDeliveryControlService.get(preDeliveryControlId));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<PreDeliveryControlDto> insert(@RequestBody @Valid PreDeliveryControlDto preDeliveryControlDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(preDeliveryControlService.save(preDeliveryControlDto));
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<PreDeliveryControlDto> update(@RequestBody @Valid PreDeliveryControlDto preDeliveryControlDto) {
        return ResponseEntity.ok().body(preDeliveryControlService.update(preDeliveryControlDto));
    }

    @DeleteMapping("/{preDeliveryControlId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("preDeliveryControlId") Long preDeliveryControlId) {
        preDeliveryControlService.delete(preDeliveryControlId);
    }

}

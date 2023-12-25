package ir.pajoohan.mce.controller;

import ir.pajoohan.mce.dto.StatusDto;
import ir.pajoohan.mce.service.StatusService;
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
@RequestMapping("/v1/status")
@Validated
public class StatusRestController {

    StatusService statusService;


    /**
     * Setters
     */
    @Autowired
    public void setStatusService(StatusService statusService) {
        this.statusService = statusService;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * Methods
     */
    @GetMapping
    @ResponseBody
    public ResponseEntity<Page<StatusDto>> getAll(@RequestParam("page") Optional<Integer> page,
                                                  @RequestParam("size") Optional<Integer> size,
                                                  @RequestParam("sort") Optional<String> sort) {

        return ResponseEntity.ok().body(statusService.getAll(page.orElse(0), size.orElse(10), sort.orElse("id")));
    }

    @GetMapping("/{statusId}")
    @ResponseBody
    public ResponseEntity<StatusDto> get(@PathVariable("statusId") Long statusId) {
        return ResponseEntity.ok().body(
                statusService.get(statusId));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<StatusDto> insert(@RequestBody @Valid StatusDto statusDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(statusService.save(statusDto));
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<StatusDto> update(@RequestBody @Valid StatusDto statusDto) {
        return ResponseEntity.ok().body(statusService.update(statusDto));
    }

    @DeleteMapping("/{statusId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("statusId") Long statusId) {
        statusService.delete(statusId);
    }

}

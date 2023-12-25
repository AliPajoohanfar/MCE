package ir.pajoohan.mce.controller;

import io.swagger.v3.oas.annotations.Operation;
import ir.pajoohan.mce.dto.AttachmentDto;
import ir.pajoohan.mce.service.AttachmentService;
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

import java.sql.SQLException;
import java.util.Optional;

@RestController
@RequestMapping("/v1/attachment")
@Validated
public class AttachmentRestController {

    AttachmentService attachmentService;

    /**
     * Setters
     */
    @Autowired
    public void setAttachmentService(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * Methods
     */
    @GetMapping
    @ResponseBody
    @Operation(summary = "Get all attachments by pagination and sort options.")
    public ResponseEntity<Page<AttachmentDto>> getAll(@RequestParam("page") Optional<Integer> page,
                                                      @RequestParam("size") Optional<Integer> size,
                                                      @RequestParam("sort") Optional<String> sort) {

        return ResponseEntity.ok().body(attachmentService.getAll(page.orElse(0), size.orElse(10), sort.orElse("id")));
    }

    @GetMapping("/{attachmentId}")
    @ResponseBody
    @Operation(summary = "Get a specific attachment by id.")
    public ResponseEntity<AttachmentDto> get(@PathVariable("attachmentId") Long attachmentId) throws SQLException {
        return ResponseEntity.ok().body(
                attachmentService.get(attachmentId));
    }

    @PostMapping
    @ResponseBody
    @Operation(summary = "Add a new attachment.")
    public ResponseEntity<AttachmentDto> insert(@RequestBody @Valid AttachmentDto attachmentDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(attachmentService.save(attachmentDto));
    }

    @PutMapping
    @ResponseBody
    @Operation(summary = "Update a specific attachment by id.")
    public ResponseEntity<AttachmentDto> update(@RequestBody @Valid AttachmentDto attachmentDto) {
        return ResponseEntity.ok().body(attachmentService.update(attachmentDto));
    }

    @DeleteMapping("/{attachmentId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Soft delete a specific attachment by id.")
    public void delete(@PathVariable("attachmentId") Long attachmentId) {
        attachmentService.delete(attachmentId);
    }

}

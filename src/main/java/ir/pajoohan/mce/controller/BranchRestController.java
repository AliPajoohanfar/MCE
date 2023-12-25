package ir.pajoohan.mce.controller;

import ir.pajoohan.mce.dto.BranchDto;
import ir.pajoohan.mce.service.BranchService;
import ir.pajoohan.mce.service.impl.BranchServiceImpl;
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
@RequestMapping("/v1/branch")
@Validated
public class BranchRestController {

    BranchService branchService;

    /**
     * Setters
     */
    @Autowired
    public void setBranchService(BranchServiceImpl branchServiceImpl) {
        this.branchService = branchServiceImpl;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * Methods
     */
    @GetMapping
    @ResponseBody
    public ResponseEntity<Page<BranchDto>> getAll(@RequestParam("page") Optional<Integer> page,
                                                  @RequestParam("size") Optional<Integer> size,
                                                  @RequestParam("sort") Optional<String> sort) {

        return ResponseEntity.ok().body(branchService.getAll(page.orElse(0), size.orElse(10), sort.orElse("id")));
    }

    @GetMapping("/{branchId}")
    @ResponseBody
    public ResponseEntity<BranchDto> get(@PathVariable("branchId") Long branchId) {
        return ResponseEntity.ok().body(branchService.get(branchId));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<BranchDto> insert(@RequestBody @Valid BranchDto branchDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(branchService.save(branchDto));
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<BranchDto> update(@RequestBody @Valid BranchDto branchDto) {
        return ResponseEntity.ok().body(branchService.update(branchDto));
    }

    @DeleteMapping("/{branchId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("branchId") Long branchId) {
        branchService.delete(branchId);
    }
}

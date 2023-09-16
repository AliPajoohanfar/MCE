package ir.pajoohan.mce.controller;

import ir.pajoohan.mce.Dto.BranchDto;
import ir.pajoohan.mce.service.BranchService;
import ir.pajoohan.mce.service.Impl.BranchServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branch")
public class BranchController {

    BranchService branchService;

    @Autowired
    public void setBranchService(BranchServiceImpl branchServiceImpl) {
        this.branchService = branchServiceImpl;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<BranchDto>> getAll() {
        return ResponseEntity.ok().body(branchService.getAll());
    }

    @GetMapping("/{branchId}")
    @ResponseBody
    public ResponseEntity<BranchDto> get(@PathVariable("branchId") Long branchId) {
        return ResponseEntity.ok().body(branchService.get(branchId));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<BranchDto> insert(@RequestBody @Valid BranchDto branchDto) {
        return ResponseEntity.ok().body(branchService.save(branchDto));
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<BranchDto> update(@RequestBody @Valid BranchDto branchDto) {
        return ResponseEntity.ok().body(branchService.update(branchDto));
    }

    @DeleteMapping("/{branchId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("branchId") Long branchId) {
        BranchDto branchDto = branchService.get(branchId);
        branchService.delete(branchDto);
    }
}

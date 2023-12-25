package ir.pajoohan.mce.controller;

import io.swagger.v3.oas.annotations.Operation;
import ir.pajoohan.mce.dto.CustomerSupportDto;
import ir.pajoohan.mce.service.CustomerSupportService;
import ir.pajoohan.mce.service.impl.CustomerSupportServiceImpl;
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
@RequestMapping("/v1/customersupport")
@Validated
public class CustomerSupportRestController {

    CustomerSupportService customerSupportService;

    /**
     * Setters
     */
    @Autowired
    public void setCustomerSupportService(CustomerSupportServiceImpl customerSupportServiceImpl) {
        this.customerSupportService = customerSupportServiceImpl;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * Methods
     */
    @GetMapping
    @ResponseBody
    @Operation(summary = "Get all customer supports by pagination and sort options.")
    public ResponseEntity<Page<CustomerSupportDto>> getAll(@RequestParam("page") Optional<Integer> page,
                                                           @RequestParam("size") Optional<Integer> size,
                                                           @RequestParam("sort") Optional<String> sort) {

        return ResponseEntity.ok().body(customerSupportService.getAll(page.orElse(0), size.orElse(10), sort.orElse("id")));
    }

    @GetMapping("/{customerSupportId}")
    @ResponseBody
    @Operation(summary = "Get a specific customer support by id.")
    public ResponseEntity<CustomerSupportDto> get(@PathVariable("customerSupportId") Long customerSupportId) {
        return ResponseEntity.ok().body(
                customerSupportService.get(customerSupportId));
    }

    @PostMapping
    @ResponseBody
    @Operation(summary = "Add a new customer support.")
    public ResponseEntity<CustomerSupportDto> insert(@RequestBody @Valid CustomerSupportDto customerSupportDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerSupportService.save(customerSupportDto));
    }

    @PutMapping
    @ResponseBody
    @Operation(summary = "Update a specific customer support by id.")
    public ResponseEntity<CustomerSupportDto> update(@RequestBody @Valid CustomerSupportDto customerSupportDto) {
        return ResponseEntity.ok().body(customerSupportService.update(customerSupportDto));
    }

    @DeleteMapping("/{customerSupportId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Soft delete a specific customer support by id.")
    public void delete(@PathVariable("customerSupportId") Long customerSupportId) {
        customerSupportService.delete(customerSupportId);
    }

}

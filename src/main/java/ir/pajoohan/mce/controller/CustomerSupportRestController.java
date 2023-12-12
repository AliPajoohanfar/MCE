package ir.pajoohan.mce.controller;

import ir.pajoohan.mce.dto.CustomerSupportDto;
import ir.pajoohan.mce.service.CustomerSupportService;
import ir.pajoohan.mce.service.impl.CustomerSupportServiceImpl;
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
    public ResponseEntity<List<CustomerSupportDto>> getAll() {
        return ResponseEntity.ok().body(customerSupportService.getAll());
    }

    @GetMapping("/{customerSupportId}")
    @ResponseBody
    public ResponseEntity<CustomerSupportDto> get(@PathVariable("customerSupportId") Long customerSupportId) {
        return ResponseEntity.ok().body(
                customerSupportService.get(customerSupportId));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<CustomerSupportDto> insert(@RequestBody @Valid CustomerSupportDto customerSupportDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerSupportService.save(customerSupportDto));
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<CustomerSupportDto> update(@RequestBody @Valid CustomerSupportDto customerSupportDto) {
        return ResponseEntity.ok().body(customerSupportService.update(customerSupportDto));
    }

    @DeleteMapping("/{customerSupportId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("customerSupportId") Long customerSupportId) {
        customerSupportService.delete(customerSupportId);
    }

}
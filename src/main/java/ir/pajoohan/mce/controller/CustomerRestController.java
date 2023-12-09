package ir.pajoohan.mce.controller;

import ir.pajoohan.mce.dto.CustomerDto;
import ir.pajoohan.mce.service.CustomerService;
import ir.pajoohan.mce.service.impl.CustomerServiceImpl;
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
@RequestMapping("/v1/customer")
@Validated
public class CustomerRestController {


    CustomerService customerService;

    /**
     * Setters
     */
    @Autowired
    public void setCustomerService(CustomerServiceImpl customerServiceImpl) {
        this.customerService = customerServiceImpl;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * Methods
     */
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<CustomerDto>> getAll() {
        return ResponseEntity.ok().body(customerService.getAll());
    }

    @GetMapping("/{customerId}")
    @ResponseBody
    public ResponseEntity<CustomerDto> get(@PathVariable("customerId") Long customerId) {
        return ResponseEntity.ok().body(
                customerService.get(customerId));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<CustomerDto> insert(@RequestBody @Valid CustomerDto customerDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(customerDto));
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<CustomerDto> update(@RequestBody @Valid CustomerDto customerDto) {
        return ResponseEntity.ok().body(customerService.update(customerDto));
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("customerId") Long customerId) {
        customerService.delete(customerId);
    }

}

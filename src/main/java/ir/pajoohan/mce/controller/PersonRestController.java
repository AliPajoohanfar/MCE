package ir.pajoohan.mce.controller;

import io.swagger.v3.oas.annotations.Operation;
import ir.pajoohan.mce.dto.PersonDto;
import ir.pajoohan.mce.service.PersonService;
import ir.pajoohan.mce.service.impl.PersonServiceImpl;
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
@RequestMapping("/v1/person")
@Validated
public class PersonRestController {

    PersonService personService;

    /**
     * Setters
     */
    @Autowired
    public void setPersonService(PersonServiceImpl personServiceImpl) {
        this.personService = personServiceImpl;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * Methods
     */
    @GetMapping
    @ResponseBody
    @Operation(summary = "Get all persons by pagination and sort options.")
    public ResponseEntity<Page<PersonDto>> getAll(@RequestParam("page") Optional<Integer> page,
                                                  @RequestParam("size") Optional<Integer> size,
                                                  @RequestParam("sort") Optional<String> sort) {

        return ResponseEntity.ok().body(personService.getAll(page.orElse(0), size.orElse(10), sort.orElse("id")));
    }

    @GetMapping("/{personId}")
    @ResponseBody
    @Operation(summary = "Get a specific person by id.")
    public ResponseEntity<PersonDto> get(@PathVariable("personId") Long personId) {
        return ResponseEntity.ok().body(
                personService.get(personId));
    }

    @PostMapping
    @ResponseBody
    @Operation(summary = "Add a new person.")
    public ResponseEntity<PersonDto> insert(@RequestBody @Valid PersonDto personDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.save(personDto));
    }

    @PutMapping
    @ResponseBody
    @Operation(summary = "Update a specific person by id.")
    public ResponseEntity<PersonDto> update(@RequestBody @Valid PersonDto personDto) {
        return ResponseEntity.ok().body(personService.update(personDto));
    }

    @DeleteMapping("/{personId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Soft delete a specific person by id.")
    public void delete(@PathVariable("personId") Long personId) {
        personService.delete(personId);
    }

}

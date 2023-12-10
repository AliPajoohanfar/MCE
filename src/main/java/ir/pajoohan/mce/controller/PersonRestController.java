package ir.pajoohan.mce.controller;

import ir.pajoohan.mce.dto.PersonDto;
import ir.pajoohan.mce.service.PersonService;
import ir.pajoohan.mce.service.impl.PersonServiceImpl;
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
    public ResponseEntity<List<PersonDto>> getAll() {
        return ResponseEntity.ok().body(personService.getAll());
    }

    @GetMapping("/{personId}")
    @ResponseBody
    public ResponseEntity<PersonDto> get(@PathVariable("personId") Long personId) {
        return ResponseEntity.ok().body(
                personService.get(personId));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<PersonDto> insert(@RequestBody @Valid PersonDto personDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.save(personDto));
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<PersonDto> update(@RequestBody @Valid PersonDto personDto) {
        return ResponseEntity.ok().body(personService.update(personDto));
    }

    @DeleteMapping("/{personId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("personId") Long personId) {
        personService.delete(personId);
    }

}
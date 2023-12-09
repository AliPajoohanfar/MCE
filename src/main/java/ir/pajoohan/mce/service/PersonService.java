package ir.pajoohan.mce.service;

import ir.pajoohan.mce.dto.PersonDto;

import java.util.List;

public interface PersonService {

    List<PersonDto> getAll();

    PersonDto get(Long id);

    PersonDto save(PersonDto personDto);

    PersonDto update(PersonDto personDto);

    void delete(Long id);
}

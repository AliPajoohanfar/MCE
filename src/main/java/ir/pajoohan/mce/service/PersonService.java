package ir.pajoohan.mce.service;

import ir.pajoohan.mce.dto.PersonDto;
import org.springframework.data.domain.Page;

public interface PersonService {

    Page<PersonDto> getAll(Integer page, Integer size, String sort);

    PersonDto get(Long id);

    PersonDto save(PersonDto personDto);

    PersonDto update(PersonDto personDto);

    void delete(Long id);
}

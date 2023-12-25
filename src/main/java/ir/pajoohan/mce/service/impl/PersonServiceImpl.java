package ir.pajoohan.mce.service.impl;

import ir.pajoohan.mce.dto.PersonDto;
import ir.pajoohan.mce.dto.PersonMapper;
import ir.pajoohan.mce.entity.Person;
import ir.pajoohan.mce.repository.PersonRepository;
import ir.pajoohan.mce.service.PersonService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    PersonRepository personRepository;

    /**
     * Setters
     */
    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * Methods
     */
    @Override
    public Page<PersonDto> getAll(Integer page, Integer size, String sort) {

        Pageable pageable = PageRequest.of(page, size).withSort(Sort.Direction.ASC, sort);
        Page<Person> personPage = personRepository.findAll(pageable);
        List<Person> personList = personPage.stream().toList();

        List<PersonDto> personDtoList = new ArrayList<>();
        for (Person s : personList) {
            personDtoList.add(PersonMapper.INSTANCE.personToPersonDto(s));
        }
        Page<PersonDto> personDtoPage = new PageImpl<>(personDtoList, pageable, personPage.getTotalElements());

        return personDtoPage;
    }

    @Override
    public PersonDto get(Long id) {
        Person person = personRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return PersonMapper.INSTANCE.personToPersonDto(person);
    }

    @Override
    public PersonDto save(PersonDto personDto) {
        Person person = PersonMapper.INSTANCE.personDtoToPerson(personDto);
        person.setId(null);
        Person personSaved = personRepository.save(person);
        return PersonMapper.INSTANCE.personToPersonDto(personSaved);
    }

    @Override
    public PersonDto update(PersonDto personDto) {
        Optional<Person> optionalPerson = personRepository.findById(personDto.getId());
        if (optionalPerson.isEmpty()) {
            throw new EntityNotFoundException("PERSON with ID : '" + personDto.getId() + "' not found.");
        }

        Person person = optionalPerson.get();
        PersonMapper.INSTANCE.updatePersonFromDto(personDto, person);
        Person personSaved = personRepository.save(person);
        return PersonMapper.INSTANCE.personToPersonDto(personSaved);
    }

    @Override
    public void delete(Long id) {
        Person person = personRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        personRepository.delete(person);
    }
}

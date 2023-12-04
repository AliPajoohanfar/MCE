package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.AddEffectiveMapping;
import ir.pajoohan.mce.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @AddEffectiveMapping
    PersonDto personToPersonDto(Person person);

    @Mapping(target = "effectiveDate", source = "effectiveDate")
    Person personDtoToPerson(PersonDto personDto);

}

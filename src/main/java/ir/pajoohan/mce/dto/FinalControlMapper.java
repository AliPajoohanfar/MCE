package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.AddAuditMapping;
import ir.pajoohan.mce.entity.FinalControl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FinalControlMapper {

    FinalControlMapper INSTANCE = Mappers.getMapper(FinalControlMapper.class);

    @AddAuditMapping
    @Mapping(target = "personId", source = "person.id")
    @Mapping(target = "statusId", source = "status.id")
    @Mapping(target = "motorcycleId", source = "motorcycle.id")
    FinalControlDto FinalControlToFinalControlDto(FinalControl finalControl);


    @Mapping(target = "person.id", source = "personId")
    @Mapping(target = "status.id", source = "statusId")
    @Mapping(target = "motorcycle.id", source = "motorcycleId")
    FinalControl FinalControlDtoToFinalControl(FinalControlDto finalControlDto);

}

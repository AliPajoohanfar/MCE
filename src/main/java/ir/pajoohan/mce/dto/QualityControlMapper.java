package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.AddEffectiveMapping;
import ir.pajoohan.mce.entity.QualityControl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QualityControlMapper {

    QualityControlMapper INSTANCE = Mappers.getMapper(QualityControlMapper.class);

    @AddEffectiveMapping
    @Mapping(target = "qc1PersonId", source = "qc1Person.id")
    @Mapping(target = "qc2PersonId", source = "qc2Person.id")
    @Mapping(target = "qc3PersonId", source = "qc3Person.id")
    @Mapping(target = "qc1StatusId", source = "qc1Status.id")
    @Mapping(target = "qc2StatusId", source = "qc2Status.id")
    @Mapping(target = "qc3StatusId", source = "qc3Status.id")
    @Mapping(target = "motorcycleId", source = "motorcycle.id")
    QualityControlDto qualityControlToQualityControlDto(QualityControl qualityControl);

    @Mapping(target = "effectiveDate", source = "effectiveDate")
    @Mapping(target = "qc1Person.id", source = "qc1PersonId")
    @Mapping(target = "qc2Person.id", source = "qc2PersonId")
    @Mapping(target = "qc3Person.id", source = "qc3PersonId")
    @Mapping(target = "qc1Status.id", source = "qc1StatusId")
    @Mapping(target = "qc2Status.id", source = "qc2StatusId")
    @Mapping(target = "qc3Status.id", source = "qc3StatusId")
    @Mapping(target = "motorcycle.id", source = "motorcycleId")
    QualityControl qualityControlDtoToQualityControl(QualityControlDto qualityControlDto);

}

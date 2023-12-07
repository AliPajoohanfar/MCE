package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.AddEffectiveMapping;
import ir.pajoohan.mce.dto.baseDto.AddUpdateMapping;
import ir.pajoohan.mce.entity.Attachment;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface AttachmentMapper {
    AttachmentMapper INSTANCE = Mappers.getMapper(AttachmentMapper.class);

    @AddEffectiveMapping
    AttachmentDto attchmentoToattachmentDto(Attachment attachment);

    @Mapping(target = "effectiveDate", source = "effectiveDate")
    Attachment attchamentDtoToattachment(AttachmentDto attachmentDto);

    @AddUpdateMapping
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    void updateAttachmentFromDto(AttachmentDto attachmentDto, @MappingTarget Attachment attachment);
}

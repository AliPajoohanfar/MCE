package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.AddEffectiveMapping;
import ir.pajoohan.mce.entity.Attachment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AttachmentMapper {
    AttachmentMapper INSTANCE = Mappers.getMapper(AttachmentMapper.class);

    @AddEffectiveMapping
    AttachmentDto attchmentoToattachmentDto(Attachment attachment);

    @Mapping(target = "effectiveDate", source = "effectiveDate")
    Attachment attchamentDtoToattachment(AttachmentDto attachmentDto);
}

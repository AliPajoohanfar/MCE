package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.AddAuditMapping;
import ir.pajoohan.mce.entity.Attachment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AttachmentMapper {
    AttachmentMapper INSTANCE = Mappers.getMapper(AttachmentMapper.class);

    @AddAuditMapping
    AttachmentDto attchmentoToattachmentDto(Attachment attachment);

    Attachment attchamentDtoToattachment(AttachmentDto attachmentDto);
}

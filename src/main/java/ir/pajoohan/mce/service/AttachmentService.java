package ir.pajoohan.mce.service;

import ir.pajoohan.mce.dto.AttachmentDto;

import java.util.List;

public interface AttachmentService {

    List<AttachmentDto> getAll();

    AttachmentDto get(Long id);

    AttachmentDto save(AttachmentDto attachmentDto);

    AttachmentDto update(AttachmentDto attachmentDto);

    void delete(Long id);
}

package ir.pajoohan.mce.service;

import ir.pajoohan.mce.dto.AttachmentDto;

import java.sql.SQLException;
import java.util.List;

public interface AttachmentService {

    List<AttachmentDto> getAll();

    AttachmentDto get(Long id) throws SQLException;

    AttachmentDto save(AttachmentDto attachmentDto);

    AttachmentDto update(AttachmentDto attachmentDto);

    void delete(Long id);
}

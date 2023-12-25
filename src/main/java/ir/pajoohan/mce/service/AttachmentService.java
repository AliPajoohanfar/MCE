package ir.pajoohan.mce.service;

import ir.pajoohan.mce.dto.AttachmentDto;
import org.springframework.data.domain.Page;

import java.sql.SQLException;

public interface AttachmentService {

    Page<AttachmentDto> getAll(Integer page, Integer size, String sort);

    AttachmentDto get(Long id) throws SQLException;

    AttachmentDto save(AttachmentDto attachmentDto);

    AttachmentDto update(AttachmentDto attachmentDto);

    void delete(Long id);
}

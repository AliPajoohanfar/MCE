package ir.pajoohan.mce.service.impl;

import ir.pajoohan.mce.dto.AttachmentDto;
import ir.pajoohan.mce.dto.AttachmentMapper;
import ir.pajoohan.mce.entity.Attachment;
import ir.pajoohan.mce.repository.AttachmentRepository;
import ir.pajoohan.mce.service.AttachmentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AttachmentServiceImpl implements AttachmentService {

    AttachmentRepository attachmentRepository;

    /**
     * Setters
     */
    @Autowired
    public void setAttachmentRepository(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * Methods
     */
    @Override
    public List<AttachmentDto> getAll() {
        List<Attachment> attachmentList = attachmentRepository.findAll();
        List<AttachmentDto> attachmentDtoList = new ArrayList<>();
        for (Attachment a : attachmentList) {
            attachmentDtoList.add(AttachmentMapper.INSTANCE.attchmentoToattachmentDto(a));
        }
        return attachmentDtoList;
    }

    @Override
    public AttachmentDto get(Long id) throws SQLException {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return AttachmentMapper.INSTANCE.attchmentoToattachmentDto(attachment);
    }

    @Override
    public AttachmentDto save(AttachmentDto attachmentDto) {
        Attachment attachment = AttachmentMapper.INSTANCE.attchamentDtoToattachment(attachmentDto);
        attachment.setId(null);
        Attachment attachmentSaved = attachmentRepository.save(attachment);
        return AttachmentMapper.INSTANCE.attchmentoToattachmentDto(attachmentSaved);
    }

    @Override
    public AttachmentDto update(AttachmentDto attachmentDto) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(attachmentDto.getId());
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            AttachmentMapper.INSTANCE.updateAttachmentFromDto(attachmentDto, attachment);
            Attachment attachmentSaved = attachmentRepository.save(attachment);
            return AttachmentMapper.INSTANCE.attchmentoToattachmentDto(attachmentSaved);
        } else {
            throw new EntityNotFoundException("ATTACHMENT with ID : '" + attachmentDto.getId() + "' not found.");
        }
    }

    @Override
    public void delete(Long id) {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        attachmentRepository.delete(attachment);
    }
}

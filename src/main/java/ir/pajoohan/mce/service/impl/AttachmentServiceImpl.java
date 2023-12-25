package ir.pajoohan.mce.service.impl;

import ir.pajoohan.mce.dto.AttachmentDto;
import ir.pajoohan.mce.dto.AttachmentMapper;
import ir.pajoohan.mce.entity.Attachment;
import ir.pajoohan.mce.repository.AttachmentRepository;
import ir.pajoohan.mce.service.AttachmentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<AttachmentDto> getAll(Integer page, Integer size, String sort) {

        Pageable pageable = PageRequest.of(page, size).withSort(Sort.Direction.ASC, sort);
        Page<Attachment> attachmentPage = attachmentRepository.findAll(pageable);
        List<Attachment> attachmentList = attachmentPage.stream().toList();

        List<AttachmentDto> attachmentDtoList = new ArrayList<>();
        for (Attachment s : attachmentList) {
            attachmentDtoList.add(AttachmentMapper.INSTANCE.attachmentToAttachmentDto(s));
        }
        Page<AttachmentDto> attachmentDtoPage = new PageImpl<>(attachmentDtoList, pageable, attachmentPage.getTotalElements());

        return attachmentDtoPage;
    }

    @Override
    public AttachmentDto get(Long id) throws SQLException {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return AttachmentMapper.INSTANCE.attachmentToAttachmentDto(attachment);
    }

    @Override
    public AttachmentDto save(AttachmentDto attachmentDto) {
        Attachment attachment = AttachmentMapper.INSTANCE.attachmentDtoToAttachment(attachmentDto);
        attachment.setId(null);
        Attachment attachmentSaved = attachmentRepository.save(attachment);
        return AttachmentMapper.INSTANCE.attachmentToAttachmentDto(attachmentSaved);
    }

    @Override
    public AttachmentDto update(AttachmentDto attachmentDto) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(attachmentDto.getId());

        if (optionalAttachment.isEmpty()) {
            throw new EntityNotFoundException("ATTACHMENT with ID : '" + attachmentDto.getId() + "' not found.");
        }
        Attachment attachment = optionalAttachment.get();
        AttachmentMapper.INSTANCE.updateAttachmentFromDto(attachmentDto, attachment);
        Attachment attachmentSaved = attachmentRepository.save(attachment);
        return AttachmentMapper.INSTANCE.attachmentToAttachmentDto(attachmentSaved);
    }

    @Override
    public void delete(Long id) {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        attachmentRepository.delete(attachment);
    }
}

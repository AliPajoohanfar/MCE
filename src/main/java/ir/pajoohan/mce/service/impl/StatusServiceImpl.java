package ir.pajoohan.mce.service.impl;

import ir.pajoohan.mce.dto.StatusDto;
import ir.pajoohan.mce.dto.StatusMapper;
import ir.pajoohan.mce.entity.Status;
import ir.pajoohan.mce.repository.StatusRepository;
import ir.pajoohan.mce.service.StatusService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StatusServiceImpl implements StatusService {

    StatusRepository statusRepository;

    /**
     * Setters
     */
    @Autowired
    public void setStatusRepository(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * Methods
     */
    @Override
    public Page<StatusDto> getAll(Integer page, Integer size, String sort) {

        Pageable pageable = PageRequest.of(page, size).withSort(Sort.Direction.ASC, sort);
        Page<Status> statusPage = statusRepository.findAll(pageable);
        List<Status> statusList = statusPage.stream().toList();

        List<StatusDto> statusDtoList = new ArrayList<>();
        for (Status s : statusList) {
            statusDtoList.add(StatusMapper.INSTANCE.statusToStatusDto(s));
        }
        Page<StatusDto> statusDtoPage = new PageImpl<>(statusDtoList, pageable, statusPage.getTotalElements());

        return statusDtoPage;
    }

    @Override
    public StatusDto get(Long id) {
        Status status = statusRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return StatusMapper.INSTANCE.statusToStatusDto(status);
    }

    @Override
    public StatusDto save(StatusDto statusDto) {
        Status status = StatusMapper.INSTANCE.statusDtoToStatus(statusDto);
        status.setId(null);
        Status statusSaved = statusRepository.save(status);
        return StatusMapper.INSTANCE.statusToStatusDto(statusSaved);
    }

    @Override
    public StatusDto update(StatusDto statusDto) {
        Optional<Status> optionalStatus = statusRepository.findById(statusDto.getId());
        if (optionalStatus.isEmpty()) {
            throw new EntityNotFoundException("STATUS with ID : '" + statusDto.getId() + "' not found.");
        }

        Status status = optionalStatus.get();
        StatusMapper.INSTANCE.updateStatusFromDto(statusDto, status);
        Status statusSaved = statusRepository.save(status);
        return StatusMapper.INSTANCE.statusToStatusDto(statusSaved);
    }

    @Override
    public void delete(Long id) {
        Status status = statusRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        statusRepository.delete(status);
    }
}

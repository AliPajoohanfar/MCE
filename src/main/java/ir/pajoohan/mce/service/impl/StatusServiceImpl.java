package ir.pajoohan.mce.service.impl;

import ir.pajoohan.mce.dto.StatusDto;
import ir.pajoohan.mce.dto.StatusMapper;
import ir.pajoohan.mce.entity.Status;
import ir.pajoohan.mce.repository.StatusRepository;
import ir.pajoohan.mce.service.StatusService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<StatusDto> getAll() {
        List<Status> statusList = statusRepository.findAll();
        List<StatusDto> statusDtoList = new ArrayList<>();
        for (Status s : statusList) {
            statusDtoList.add(StatusMapper.INSTANCE.statusToStatusDto(s));
        }
        return statusDtoList;
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

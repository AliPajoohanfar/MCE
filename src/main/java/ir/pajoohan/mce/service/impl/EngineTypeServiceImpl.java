package ir.pajoohan.mce.service.impl;

import ir.pajoohan.mce.dto.EngineTypeDto;
import ir.pajoohan.mce.dto.EngineTypeMapper;
import ir.pajoohan.mce.entity.EngineType;
import ir.pajoohan.mce.repository.EngineTypeRepository;
import ir.pajoohan.mce.service.EngineTypeService;
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
public class EngineTypeServiceImpl implements EngineTypeService {

    EngineTypeRepository engineTypeRepository;

    /**
     * Setters
     */
    @Autowired
    public void setEngineTypeRepository(EngineTypeRepository engineTypeRepository) {
        this.engineTypeRepository = engineTypeRepository;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * Methods
     */
    @Override
    public Page<EngineTypeDto> getAll(Integer page, Integer size, String sort) {

        Pageable pageable = PageRequest.of(page, size).withSort(Sort.Direction.ASC, sort);
        Page<EngineType> engineTypePage = engineTypeRepository.findAll(pageable);
        List<EngineType> engineTypeList = engineTypePage.stream().toList();

        List<EngineTypeDto> engineTypeDtoList = new ArrayList<>();
        for (EngineType e : engineTypeList) {
            engineTypeDtoList.add(EngineTypeMapper.INSTANCE.engineTypeToEngineTypeDto(e));
        }
        Page<EngineTypeDto> engineTypeDtoPage = new PageImpl<>(engineTypeDtoList, pageable, engineTypePage.getTotalElements());

        return engineTypeDtoPage;
    }

    @Override
    public EngineTypeDto get(Long id) {
        EngineType engineType = engineTypeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return EngineTypeMapper.INSTANCE.engineTypeToEngineTypeDto(engineType);
    }

    @Override
    public EngineTypeDto save(EngineTypeDto engineTypeDto) {
        EngineType engineType = EngineTypeMapper.INSTANCE.engineTypeDtoToEngineType(engineTypeDto);
        engineType.setId(null);
        EngineType engineTypeSaved = engineTypeRepository.save(engineType);
        return EngineTypeMapper.INSTANCE.engineTypeToEngineTypeDto(engineTypeSaved);
    }

    @Override
    public EngineTypeDto update(EngineTypeDto engineTypeDto) {
        Optional<EngineType> optionalEngineType = engineTypeRepository.findById(engineTypeDto.getId());
        if (optionalEngineType.isEmpty()) {
            throw new EntityNotFoundException("ENGINE_TYPE with ID : '" + engineTypeDto.getId() + "' not found.");
        }
        EngineType engineType = optionalEngineType.get();
        EngineTypeMapper.INSTANCE.updateEngineTypeFromDto(engineTypeDto, engineType);
        EngineType engineTypeSaved = engineTypeRepository.save(engineType);
        return EngineTypeMapper.INSTANCE.engineTypeToEngineTypeDto(engineTypeSaved);
    }

    @Override
    public void delete(Long id) {
        EngineType engineType = engineTypeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        engineTypeRepository.delete(engineType);
    }
}

package ir.pajoohan.mce.service;

import ir.pajoohan.mce.dto.BranchDto;
import org.springframework.data.domain.Page;

public interface BranchService {

    Page<BranchDto> getAll(Integer page, Integer size, String sort);

    BranchDto get(Long id);

    BranchDto save(BranchDto branchDto);

    BranchDto update(BranchDto branchDto);

    void delete(Long id);

}

package ir.pajoohan.mce.service;

import ir.pajoohan.mce.dto.BranchDto;

import java.util.List;

public interface BranchService {

    List<BranchDto> getAll();

    BranchDto get(Long id);

    BranchDto save(BranchDto branchDto);

    BranchDto update(BranchDto branchDto);

    void delete(BranchDto branchDto);

}

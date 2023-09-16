package ir.pajoohan.mce.Dto;

import ir.pajoohan.mce.entity.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BranchMapper {

    BranchMapper INSTANCE = Mappers.getMapper(BranchMapper.class);

    @Mapping(target = "stateId", source = "branch.state.id")
    BranchDto branchToBranchDto(Branch branch);

    @Mapping(target = "state.id", source = "branchDto.stateId")
    Branch branchDtoToBranch(BranchDto branchDto);
}

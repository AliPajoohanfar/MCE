package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.AddEffectiveMapping;
import ir.pajoohan.mce.entity.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BranchMapper {

    BranchMapper INSTANCE = Mappers.getMapper(BranchMapper.class);

    @AddEffectiveMapping
    @Mapping(target = "stateId", source = "branch.state.id")
    @Mapping(target = "personId", source = "branch.person.id")
    @Mapping(target = "parentId", source = "branch.parent.id")
    BranchDto branchToBranchDto(Branch branch);

    @Mapping(target = "effectiveDate", source = "effectiveDate")
    @Mapping(target = "state.id", source = "branchDto.stateId")
    @Mapping(target = "person.id", source = "branchDto.personId")
    @Mapping(target = "parent.id", source = "branchDto.parentId")
    Branch branchDtoToBranch(BranchDto branchDto);
}

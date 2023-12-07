package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.AddEffectiveMapping;
import ir.pajoohan.mce.dto.baseDto.AddUpdateMapping;
import ir.pajoohan.mce.entity.Branch;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public abstract class BranchMapper {

    public static BranchMapper INSTANCE = Mappers.getMapper(BranchMapper.class);

    @AddEffectiveMapping
    @Mapping(target = "stateId", source = "branch.state.id")
    @Mapping(target = "personId", source = "branch.person.id")
    @Mapping(target = "parentId", source = "branch.parent.id")
    public abstract BranchDto branchToBranchDto(Branch branch);

    @Mapping(target = "effectiveDate", source = "effectiveDate")
    @Mapping(target = "state.id", source = "branchDto.stateId")
    @Mapping(target = "person.id", source = "branchDto.personId")
    @Mapping(target = "parent.id", source = "branchDto.parentId")
    public abstract Branch branchDtoToBranch(BranchDto branchDto);

    @AddUpdateMapping
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    public abstract void updateBranchFromDto(BranchDto branchDto, @MappingTarget Branch branch);

    @AfterMapping
    protected Branch doAfterMapping(@MappingTarget Branch branch) {
        if (branch != null && branch.getParent() != null && branch.getParent().getId() == null) {
            branch.setParent(null);
        }
        return branch;
    }
}

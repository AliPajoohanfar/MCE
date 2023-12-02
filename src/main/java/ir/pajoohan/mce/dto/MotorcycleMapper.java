package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.AddAuditMapping;
import ir.pajoohan.mce.entity.Motorcycle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MotorcycleMapper {

    MotorcycleMapper INSTANCE = Mappers.getMapper(MotorcycleMapper.class);

    @AddAuditMapping
    @Mapping(target = "motorcycleTypeId", source = "motorcycle.motorcycleType.id")
    @Mapping(target = "branchId", source = "motorcycle.branch.id")
    @Mapping(target = "customerId", source = "motorcycle.customer.id")
    @Mapping(target = "warehouseInputId", source = "motorcycle.warehouseInput.id")
    @Mapping(target = "colorId", source = "motorcycle.color.id")
    @Mapping(target = "statusId", source = "motorcycle.status.id")
    @Mapping(target = "subBranchId", source = "motorcycle.subBranch.id")
    @Mapping(target = "stateId", source = "motorcycle.state.id")
    @Mapping(target = "attachmentId", source = "motorcycle.attachment.id")
    MotorcycleDto MotorcycleToMotorcycleDto(Motorcycle motorcycle);

    @Mapping(target = "motorcycleType.id", source = "motorcycleTypeId")
    @Mapping(target = "branch.id", source = "branchId")
    @Mapping(target = "customer.id", source = "customerId")
    @Mapping(target = "warehouseInput.id", source = "warehouseInputId")
    @Mapping(target = "color.id", source = "colorId")
    @Mapping(target = "status.id", source = "statusId")
    @Mapping(target = "subBranch.id", source = "subBranchId")
    @Mapping(target = "state.id", source = "stateId")
    @Mapping(target = "attachment.id", source = "attachmentId")
    Motorcycle MotorcycleDtoToMotorcycle(MotorcycleDto motorcycleDto);
}

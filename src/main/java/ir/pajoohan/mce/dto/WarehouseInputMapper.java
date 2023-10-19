package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.entity.WarehouseInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WarehouseInputMapper {

    WarehouseInputMapper INSTANCE = Mappers.getMapper(WarehouseInputMapper.class);

    @Mapping(target = "controllerPersonId", source = "warehouseInput.controllerPerson.id")
    @Mapping(target = "identifiersAttachId", source = "warehouseInput.identifiersAttach.id")
    @Mapping(target = "receiptsAttachId", source = "warehouseInput.receiptsAttach.id")
    @Mapping(target = "reportsAttachId", source = "warehouseInput.reportsAttach.id")
    @Mapping(target = "prodPermissionPersonId", source = "warehouseInput.prodPermissionPerson.id")
    @Mapping(target = "engineTypeId", source = "warehouseInput.engineType.id")
    WarehouseInputDto warehouseInputToWarehouseInputDto(WarehouseInput warehouseInput);

    @Mapping(target = "controllerPerson.id", source = "warehouseInputDto.controllerPersonId")
    @Mapping(target = "identifiersAttach.id", source = "warehouseInputDto.identifiersAttachId")
    @Mapping(target = "receiptsAttach.id", source = "warehouseInputDto.receiptsAttachId")
    @Mapping(target = "reportsAttach.id", source = "warehouseInputDto.reportsAttachId")
    @Mapping(target = "prodPermissionPerson.id", source = "warehouseInputDto.prodPermissionPersonId")
    @Mapping(target = "engineType.id", source = "warehouseInputDto.engineTypeId")
    WarehouseInput warehouseInputDtoToWarehouseInput(WarehouseInputDto warehouseInputDto);
}

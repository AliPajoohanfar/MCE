package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.AddEffectiveMapping;
import ir.pajoohan.mce.dto.baseDto.AddUpdateMapping;
import ir.pajoohan.mce.entity.WarehouseInput;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public abstract class WarehouseInputMapper {

    public static WarehouseInputMapper INSTANCE = Mappers.getMapper(WarehouseInputMapper.class);

    @AddEffectiveMapping
    @Mapping(target = "controllerPersonId", source = "warehouseInput.controllerPerson.id")
    @Mapping(target = "identifiersAttachId", source = "warehouseInput.identifiersAttach.id")
    @Mapping(target = "receiptsAttachId", source = "warehouseInput.receiptsAttach.id")
    @Mapping(target = "reportsAttachId", source = "warehouseInput.reportsAttach.id")
    @Mapping(target = "prodPermissionPersonId", source = "warehouseInput.prodPermissionPerson.id")
    @Mapping(target = "engineTypeId", source = "warehouseInput.engineType.id")
    public abstract WarehouseInputDto warehouseInputToWarehouseInputDto(WarehouseInput warehouseInput);

    @Mapping(target = "effectiveDate", source = "effectiveDate")
    @Mapping(target = "controllerPerson.id", source = "warehouseInputDto.controllerPersonId")
    @Mapping(target = "identifiersAttach.id", source = "warehouseInputDto.identifiersAttachId")
    @Mapping(target = "receiptsAttach.id", source = "warehouseInputDto.receiptsAttachId")
    @Mapping(target = "reportsAttach.id", source = "warehouseInputDto.reportsAttachId")
    @Mapping(target = "prodPermissionPerson.id", source = "warehouseInputDto.prodPermissionPersonId")
    @Mapping(target = "engineType.id", source = "warehouseInputDto.engineTypeId")
    public abstract WarehouseInput warehouseInputDtoToWarehouseInput(WarehouseInputDto warehouseInputDto);

    @AddUpdateMapping
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    public abstract void updateWarehouseInputFromDto(WarehouseInputDto warehouseInputDto, @MappingTarget WarehouseInput warehouseInput);

    @AfterMapping
    protected WarehouseInput doAfterMapping(@MappingTarget WarehouseInput warehouseInput) {
        if (warehouseInput != null) {
            if (warehouseInput.getIdentifiersAttach() != null && warehouseInput.getIdentifiersAttach().getId() == null) {
                warehouseInput.setIdentifiersAttach(null);
            }
            if (warehouseInput.getReceiptsAttach() != null && warehouseInput.getReceiptsAttach().getId() == null) {
                warehouseInput.setReceiptsAttach(null);
            }
            if (warehouseInput.getReportsAttach() != null && warehouseInput.getReportsAttach().getId() == null) {
                warehouseInput.setReportsAttach(null);
            }

            if (warehouseInput.getControllerPerson() != null && warehouseInput.getControllerPerson().getId() == null) {
                warehouseInput.setControllerPerson(null);
            }
            if (warehouseInput.getProdPermissionPerson() != null && warehouseInput.getProdPermissionPerson().getId() == null) {
                warehouseInput.setProdPermissionPerson(null);
            }
        }
        return warehouseInput;
    }
}

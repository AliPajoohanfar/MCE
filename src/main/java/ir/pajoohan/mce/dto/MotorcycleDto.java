package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.EffectiveDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@Component
public class MotorcycleDto extends EffectiveDto {

    private Long id;
    private String engineMum;
    private String chassisNum;
    private Date numberingDate;
    private Date exitDate;
    private String licensePlate;
    private String waybillNum;
    private Date customerBuyDate;

    private Long motorcycleTypeId;
    private Long branchId;
    private Long customerId;
    private Long warehouseInputId;
    private Long colorId;
    private Long statusId;
    private Long subBranchId;
    private Long stateId;
    private Long attachmentId;
}

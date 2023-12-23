package ir.pajoohan.mce.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date numberingDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date exitDate;

    private String licensePlate;
    private String waybillNum;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date customerBuyDate;

    private Long motorcycleTypeId;
    private Long branchId;
    private Long customerId;
    private Long warehouseInputId;
    private Long colorId;
    private Long statusId;
    private Long stateId;
    private Long attachmentId;
}

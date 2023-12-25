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
public class WarehouseInputDto extends EffectiveDto {

    private Long id;
    private String bomLinkUrl;
    private Integer year;
    private String kootajNum;
    private Integer num;
    private Long engineNumStart;
    private Long engineNumEnd;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date inputDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date controlDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date prodPermissionDate;

    private String receiptNumber;
    private Long controllerPersonId;
    private Long identifiersAttachId;
    private Long receiptsAttachId;
    private Long reportsAttachId;
    private Long prodPermissionPersonId;
    private Long engineTypeId;
}

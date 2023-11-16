package ir.pajoohan.mce.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@Component
public class WarehouseInputDto {

    String bomLinkUrl;
    Integer year;
    String kootajNum;
    private Long id;
    private Integer num;
    private Long engineNumStart;
    private Long engineNumEnd;
    private Date inputDate;
    private Date controlDate;
    private Date prodPermissionDate;
    private String receiptNumber;
    private Long controllerPersonId;
    private Long identifiersAttachId;
    private Long receiptsAttachId;
    private Long reportsAttachId;
    private Long prodPermissionPersonId;
    private Long engineTypeId;
}
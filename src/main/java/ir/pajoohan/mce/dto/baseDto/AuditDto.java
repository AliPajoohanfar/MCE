package ir.pajoohan.mce.dto.baseDto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AuditDto {
    private String createdBy;
    private Date createdDate;
    private String lastModifiedBy;
    private Date lastModifiedDate;
}
package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.AuditDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class FinalControlDto extends AuditDto {
    private Long id;
    private String failureTitle;
    private String failureDesc;
    private String correctiveAction;
    private String description;

    private Long personId;
    private Long statusId;
    private Long motorcycleId;
}

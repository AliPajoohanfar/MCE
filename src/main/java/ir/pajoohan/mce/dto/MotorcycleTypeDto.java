package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.AuditDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class MotorcycleTypeDto extends AuditDto {

    private Long id;
    private String code;
    private String name;
    private Long informationAttachId;
}

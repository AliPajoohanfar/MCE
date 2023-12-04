package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.EffectiveDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class MotorcycleTypeDto extends EffectiveDto {

    private Long id;
    private String code;
    private String name;
    private Long informationAttachId;
}

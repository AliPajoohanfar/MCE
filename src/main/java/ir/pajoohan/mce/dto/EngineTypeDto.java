package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.EffectiveDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class EngineTypeDto extends EffectiveDto {
    private Long id;
    private String code;
    private String name;
    private Integer volume;
    private Integer power;
    private Integer fuel;
}

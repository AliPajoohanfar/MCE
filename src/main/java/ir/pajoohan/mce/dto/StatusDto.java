package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.EffectiveDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class StatusDto extends EffectiveDto {
    private Long id;
    private String statusFlow;
    private Integer orderNum;
    private String code;
    private String descPersian;
    private String descEnglish;
}

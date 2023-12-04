package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.EffectiveDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class PreDeliveryControlDto extends EffectiveDto {
    private Long id;
    private String failureDesc;
    private String correctiveAction;
    private String description;

    private Long personId;
    private Long statusId;
    private Long motorcycleId;
}

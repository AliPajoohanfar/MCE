package ir.pajoohan.mce.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class PreDeliveryControlDto {
    private Long id;
    private String failureDesc;
    private String correctiveAction;
    private String description;

    private Long personId;
    private Long statusId;
    private Long motorcycleId;
}

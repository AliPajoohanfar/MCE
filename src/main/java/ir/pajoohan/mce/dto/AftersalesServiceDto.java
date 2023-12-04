package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.EffectiveDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@Component
public class AftersalesServiceDto extends EffectiveDto {
    private Long id;
    private String serviceTitle;
    private String serviceDesc;
    private Date serviceDate;
    private Long kilometer;
    private Long customerId;
    private Long motorcycleId;
    private Long branchId;
}
package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.EffectiveDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class QualityControlDto extends EffectiveDto {

    private Long id;
    private String qc1Comment;
    private String qc2Comment;
    private String qc3Comment;

    private Long qc1PersonId;
    private Long qc1StatusId;
    private Long qc2PersonId;
    private Long qc2StatusId;
    private Long qc3PersonId;
    private Long qc3StatusId;
    private Long motorcycleId;
}

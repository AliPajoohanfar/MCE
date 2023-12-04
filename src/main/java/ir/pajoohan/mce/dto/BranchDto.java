package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.EffectiveDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class BranchDto extends EffectiveDto {
    private Long id;
    private Long parentId;
    private String code;
    private String name;
    private Long stateId;
    private Long personId;
    private Boolean mcDocPrintPermission;
    private Integer branchTyp;
}

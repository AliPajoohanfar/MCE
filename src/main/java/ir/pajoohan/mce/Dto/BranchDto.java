package ir.pajoohan.mce.Dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class BranchDto {
    private Long id;
    private Long parentId;
    private String code;
    private String name;
    private Long stateId;
    private Boolean McDocPrintPermission;
    private Integer branchTyp;
}

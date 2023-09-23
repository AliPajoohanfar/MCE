package ir.pajoohan.mce.dto;

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
    private Long personId;
    private Boolean McDocPrintPermission;
    private Integer branchTyp;
}

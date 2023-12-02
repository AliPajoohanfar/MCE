package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.AuditDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class CustomerSupportDto extends AuditDto {

    private Long id;
    private String customerCriticism;
    private String customerSuggestion;
    private String customerComplaint;
    private String response;
    private String customerPol;
    private Long parentId;
}

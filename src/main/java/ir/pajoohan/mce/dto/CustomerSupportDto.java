package ir.pajoohan.mce.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class CustomerSupportDto {

    private Long id;
    private String customerCriticism;
    private String customerSuggestion;
    private String customerComplaint;
    private String response;
    private String customerPol;
    private Long parentId;
}

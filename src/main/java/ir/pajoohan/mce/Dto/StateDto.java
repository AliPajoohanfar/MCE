package ir.pajoohan.mce.Dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class StateDto {
    private Long id;
    private String name;
    private String telCode;
}

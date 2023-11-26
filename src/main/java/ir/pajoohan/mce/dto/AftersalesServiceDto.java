package ir.pajoohan.mce.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@Component
public class AftersalesServiceDto {
    private Long id;
    private String serviceTitle;
    private String serviceDesc;
    private Date serviceDate;
    private Long kilometer;
    private Long customerId;
    private Long motorcycleId;
    private Long branchId;
}
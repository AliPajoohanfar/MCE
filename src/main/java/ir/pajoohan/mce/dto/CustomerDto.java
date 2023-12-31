package ir.pajoohan.mce.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import ir.pajoohan.mce.dto.baseDto.EffectiveDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@Component
public class CustomerDto extends EffectiveDto {
    private Long id;
    private String nationalCode;
    private String name;
    private String family;
    private String fatherName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthdate;

    private String identifierNum;
    private String issuancePlace;
    private String postalCode;
    private String homeAddress;
    private String mobileNumber;
    private String phoneNumber;
}

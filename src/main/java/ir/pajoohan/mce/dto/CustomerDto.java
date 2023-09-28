package ir.pajoohan.mce.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@Component
public class CustomerDto {
    private Long id;
    private String nationalCode;
    private String name;
    private String family;
    private String fatherName;
    private Date birthdate;
    private String identifierNum;
    private String issuancePlace;
    private String postalCode;
    private String homeAddress;
    private String mobileNumber;
    private String phoneNumber;
}
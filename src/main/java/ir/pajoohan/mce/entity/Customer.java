package ir.pajoohan.mce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import static ir.pajoohan.mce.entity.Customer.SCHEMA_MCE;
import static ir.pajoohan.mce.entity.Customer.TABLE_CUSTOMER;

@Getter
@Setter
@Entity
@Table(name = TABLE_CUSTOMER, schema = SCHEMA_MCE)
public class Customer {

    public static final String SCHEMA_MCE = "MCE";
    public static final String TABLE_CUSTOMER = "CUSTOMER";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "branch_generator")
    @SequenceGenerator(name = "branch_generator", sequenceName = "SQ_CUSTOMER", schema = SCHEMA_MCE, allocationSize = 1)
    private Long id;

    @NotBlank(message = "Enter CUSTOMER'S NATIONAL_CODE.")
    @Size(min = 10, max = 10, message = "CUSTOMER'S NATIONAL_CODE must be 10 characters.")
    @Column(name = "NATIONAL_CODE", unique = true, nullable = false)
    private String nationalCode;

    @NotBlank(message = "Enter CUSTOMER'S NAME.")
    @Size(min = 2, max = 200, message = "CUSTOMER'S NAME must be between 2 to 100 characters.")
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotBlank(message = "Enter CUSTOMER'S FAMILY.")
    @Size(min = 2, max = 300, message = "CUSTOMER'S FAMILY must be between 2 to 100 characters.")
    @Column(name = "FAMILY", nullable = false)
    private String family;

    @Column(name = "FATHER_NAME")
    @Size(min = 2, max = 200, message = "CUSTOMER'S FAMILY must be between 2 to 100 characters.")
    private String fatherName;

    @Column(name = "BIRTHDATE")
    private Date birthdate;

    @Size(min = 1, max = 10, message = "CUSTOMER'S IDENTIFIRE_NUM must be between 2 to 100 characters.")
    @Column(name = "IDENTIFIRE_NUM")
    private String identifierNum;

    @Size(min = 2, max = 200, message = "CUSTOMER'S ISSUANCE_PLACE must be between 2 to 100 characters.")
    @Column(name = "ISSUANCE_PLACE")
    private String issuancePlace;

    @Size(min = 10, max = 10, message = "CUSTOMER'S POSTAL_CODE must be 10 characters.")
    @Column(name = "POSTAL_CODE")
    private String postalCode;

    @Size(min = 2, max = 500, message = "CUSTOMER'S ISSUANCE_PLACE must be between 2 to 500 characters.")
    @Column(name = "HOME_ADDRESS")
    private String homeAddress;


    @NotBlank(message = "Enter CUSTOMER'S MOBILE_NUMBER.")
    @Size(min = 10, max = 10, message = "CUSTOMER'S MOBILE_NUMBER 10 characters.")
    @Column(name = "MOBILE_NUMBER", nullable = false)
    private String mobileNumber;

    @Size(min = 10, max = 10, message = "CUSTOMER'S PHONE_NUMBER 10 characters.")
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

}

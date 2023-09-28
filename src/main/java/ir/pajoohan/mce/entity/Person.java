package ir.pajoohan.mce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import static ir.pajoohan.mce.entity.Person.SCHEMA_MCE;
import static ir.pajoohan.mce.entity.Person.TABLE_PERSON;

@Getter
@Setter
@Entity
@Table(name = TABLE_PERSON, schema = SCHEMA_MCE)
public class Person {

    public static final String SCHEMA_MCE = "MCE";
    public static final String TABLE_PERSON = "PERSON";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_generator")
    @SequenceGenerator(name = "person_generator", sequenceName = "SQ_PERSON", schema = SCHEMA_MCE, allocationSize = 1)
    private Long id;

    @NotBlank(message = "Enter PERSON'S NATIONAL_CODE.")
    @Size(min = 2, max = 10, message = "PERSON'S NATIONAL_CODE must between 2 to 10 characters.")
    @Column(name = "NATIONAL_CODE", length = 10, nullable = false, unique = true)
    private String nationalCode;

    @NotBlank(message = "Enter PERSON'S NAME.")
    @Size(min = 2, max = 200, message = "PERSON'S NAME must between 2 to 200 characters.")
    @Column(name = "NAME", length = 200, nullable = false)
    private String name;

    @NotBlank(message = "Enter PERSON'S FAMILY.")
    @Size(min = 2, max = 300, message = "PERSON'S FAMILY must between 2 to 300 characters.")
    @Column(name = "FAMILY", length = 300, nullable = false)
    private String family;

    @Size(min = 2, max = 200, message = "PERSON'S FATHER_NAME must between 2 to 200 characters.")
    @Column(name = "FATHER_NAME")
    private String fatherName;

    @Column(name = "BIRTHDATE")
    private Date birthDate;

    @Size(min = 2, max = 10, message = "PERSON'S IDENTIFIRE_NUM must between 2 to 10 characters.")
    @Column(name = "IDENTIFIRE_NUM", length = 10)
    private String identifierNumb;

    @Size(min = 2, max = 200, message = "PERSON'S ISSUANCE_PLACE must between 2 to 200 characters.")
    @Column(name = "ISSUANCE_PLACE", length = 200)
    private String issuancePlace;

    @Size(min = 2, max = 10, message = "PERSON'S POSTAL_CODE must between 2 to 10 characters.")
    @Column(name = "POSTAL_CODE", length = 10)
    private String postalCode;

    @Size(min = 2, max = 500, message = "PERSON'S HOME_ADDRESS must between 2 to 500 characters.")
    @Column(name = "HOME_ADDRESS", length = 500)
    private String homeAddress;

    @Size(min = 10, max = 10, message = "PERSON'S MOBILE_NUMBER must 10 characters.")
    @Column(name = "MOBILE_NUMBER", length = 10, unique = true)
    private String mobileNumber;

    @NotBlank(message = "Enter PERSON'S PHONE_NUMBER.")
    @Size(min = 10, max = 10, message = "PERSON'S PHONE_NUMBER must 10 characters.")
    @Column(name = "PHONE_NUMBER", length = 10, nullable = false)
    private String phoneNumber;

}
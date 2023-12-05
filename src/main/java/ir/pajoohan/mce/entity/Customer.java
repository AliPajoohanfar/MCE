package ir.pajoohan.mce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.pajoohan.mce.entity.baseModel.Effective;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import java.util.Date;
import java.util.List;

import static ir.pajoohan.mce.entity.Customer.SCHEMA_MCE;
import static ir.pajoohan.mce.entity.Customer.TABLE;

@Getter
@Setter
@Entity
@Table(name = TABLE, schema = SCHEMA_MCE)
@SQLDelete(sql = "UPDATE " + State.TABLE + " SET DISABLE_DATE = TRUNC(CURRENT_DATE) WHERE id = ?")
public class Customer extends Effective {

    public static final String SCHEMA_MCE = "MCE";
    public static final String TABLE = "CUSTOMER";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "branch_generator")
    @SequenceGenerator(name = "branch_generator", sequenceName = "SQ_" + TABLE, schema = SCHEMA_MCE, allocationSize = 1)
    private Long id;

    @NotBlank(message = "Enter CUSTOMER'S NATIONAL_CODE.")
    @Size(min = 10, max = 10, message = "CUSTOMER'S NATIONAL_CODE must be 10 characters.")
    @Pattern(regexp = "^[0-9]\\d*$", message = "CUSTOMER'S NATIONAL_CODE must be digits only")
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

    @PastOrPresent(message = "CUSTOMER'S BIRTHDATE must be past or present.")
    @Column(name = "BIRTHDATE")
    private Date birthdate;

    @Size(min = 1, max = 10, message = "CUSTOMER'S IDENTIFIRE_NUM must be between 2 to 100 characters.")
    @Pattern(regexp = "^[0-9]\\d*$", message = "CUSTOMER'S IDENTIFIRE_NUM must be digits only")
    @Column(name = "IDENTIFIRE_NUM")
    private String identifierNum;

    @Size(min = 2, max = 200, message = "CUSTOMER'S ISSUANCE_PLACE must be between 2 to 100 characters.")
    @Column(name = "ISSUANCE_PLACE")
    private String issuancePlace;

    @Size(min = 10, max = 10, message = "CUSTOMER'S POSTAL_CODE must be 10 characters.")
    @Pattern(regexp = "^[0-9]\\d*$", message = "CUSTOMER'S POSTAL_CODE must be digits only")
    @Column(name = "POSTAL_CODE")
    private String postalCode;

    @Size(min = 2, max = 500, message = "CUSTOMER'S ISSUANCE_PLACE must be between 2 to 500 characters.")
    @Column(name = "HOME_ADDRESS")
    private String homeAddress;

    @NotBlank(message = "Enter CUSTOMER'S MOBILE_NUMBER.")
    @Size(min = 10, max = 10, message = "CUSTOMER'S MOBILE_NUMBER 10 characters.")
    @Pattern(regexp = "^[0-9]\\d*$", message = "CUSTOMER'S MOBILE_NUMBER must be digits only")
    @Column(name = "MOBILE_NUMBER", nullable = false)
    private String mobileNumber;

    @Size(min = 10, max = 10, message = "CUSTOMER'S PHONE_NUMBER 10 characters.")
    @Pattern(regexp = "^[0-9]\\d*$", message = "CUSTOMER'S PHONE_NUMBER must be digits only")
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Email(message = "Enter valid CUSTOMER'S EMAIL")
    @Column(name = "EMAIL", length = 250)
    private String email;
    /*----------------------------------------------------------------------------------------------------------------*/

    @JsonIgnore
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Motorcycle> motorcycleList;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AftersalesService> aftersalesServiceList;


}

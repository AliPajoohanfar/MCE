package ir.pajoohan.mce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.pajoohan.mce.entity.baseModel.Effective;
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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import java.util.Date;
import java.util.List;

import static ir.pajoohan.mce.entity.Person.SCHEMA_MCE;
import static ir.pajoohan.mce.entity.Person.TABLE;

@Getter
@Setter
@Entity
@Table(name = TABLE, schema = SCHEMA_MCE)
@SQLDelete(sql = "UPDATE " + TABLE + " SET DISABLE_DATE = TRUNC(CURRENT_DATE) WHERE id = ? and DISABLE_DATE is null")
public class Person extends Effective {

    public static final String SCHEMA_MCE = "MCE";
    public static final String TABLE = "PERSON";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_generator")
    @SequenceGenerator(name = "person_generator", sequenceName = "SQ_" + TABLE, schema = SCHEMA_MCE, allocationSize = 1)
    private Long id;

    @NotBlank(message = "Enter PERSON'S NATIONAL_CODE.")
    @Size(min = 2, max = 10, message = "PERSON'S NATIONAL_CODE must be between 2 to 10 characters.")
    @Pattern(regexp = "^[0-9]\\d*$", message = "PERSON'S NATIONAL_CODE must be digits only")
    @Column(name = "NATIONAL_CODE", length = 10, nullable = false, unique = true)
    private String nationalCode;

    @NotBlank(message = "Enter PERSON'S NAME.")
    @Size(min = 2, max = 200, message = "PERSON'S NAME must be between 2 to 200 characters.")
    @Column(name = "NAME", length = 200, nullable = false)
    private String name;

    @NotBlank(message = "Enter PERSON'S FAMILY.")
    @Size(min = 2, max = 300, message = "PERSON'S FAMILY must be between 2 to 300 characters.")
    @Column(name = "FAMILY", length = 300, nullable = false)
    private String family;

    @Size(min = 2, max = 200, message = "PERSON'S FATHER_NAME must be between 2 to 200 characters.")
    @Column(name = "FATHER_NAME")
    private String fatherName;

    @PastOrPresent(message = "PERSON'S BIRTHDATE must be past or present.")
    @Column(name = "BIRTHDATE")
    private Date birthDate;

    @Size(min = 2, max = 10, message = "PERSON'S IDENTIFIRE_NUM must be between 2 to 10 characters.")
    @Column(name = "IDENTIFIRE_NUM", length = 10)
    private String identifierNum;

    @Size(min = 2, max = 200, message = "PERSON'S ISSUANCE_PLACE must be between 2 to 200 characters.")
    @Column(name = "ISSUANCE_PLACE", length = 200)
    private String issuancePlace;

    @Size(min = 2, max = 10, message = "PERSON'S POSTAL_CODE must be between 2 to 10 characters.")
    @Pattern(regexp = "^[0-9]\\d*$", message = "PERSON'S POSTAL_CODE must be digits only")
    @Column(name = "POSTAL_CODE", length = 10)
    private String postalCode;

    @Size(min = 2, max = 500, message = "PERSON'S HOME_ADDRESS must be between 2 to 500 characters.")
    @Column(name = "HOME_ADDRESS", length = 500)
    private String homeAddress;

    @Size(min = 10, max = 10, message = "PERSON'S MOBILE_NUMBER must be 10 characters.")
    @Pattern(regexp = "^[0-9]\\d*$", message = "PERSON'S MOBILE_NUMBER must be digits only")
    @Column(name = "MOBILE_NUMBER", length = 10, unique = true)
    private String mobileNumber;

    @NotNull(message = "Enter PERSON'S PHONE_NUMBER.")
    @Size(min = 10, max = 10, message = "PERSON'S PHONE_NUMBER must be 10 characters.")
    @Pattern(regexp = "^[0-9]\\d*$", message = "PERSON'S PHONE_NUMBER must be digits only")
    @Column(name = "PHONE_NUMBER", length = 10, nullable = false)
    private String phoneNumber;

    @Email(message = "Enter valid PERSON'S EMAIL")
    @Column(name = "EMAIL", length = 250)
    private String email;
    /*----------------------------------------------------------------------------------------------------------------*/

    @JsonIgnore
    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private List<Branch> branchList;

    @JsonIgnore
    @OneToMany(mappedBy = "controllerPerson", fetch = FetchType.LAZY)
    private List<WarehouseInput> warehouseInputsControllerList;

    @JsonIgnore
    @OneToMany(mappedBy = "prodPermissionPerson", fetch = FetchType.LAZY)
    private List<WarehouseInput> warehouseInputsProdPermList;

    @JsonIgnore
    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private List<FinalControl> finalControlList;

    @JsonIgnore
    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private List<PreDeliveryControl> preDeliveryControlList;

    @JsonIgnore
    @OneToMany(mappedBy = "qc1Person", fetch = FetchType.LAZY)
    private List<QualityControl> qc1QualityControlList;

    @JsonIgnore
    @OneToMany(mappedBy = "qc2Person", fetch = FetchType.LAZY)
    private List<QualityControl> qc2QualityControlList;

    @JsonIgnore
    @OneToMany(mappedBy = "qc3Person", fetch = FetchType.LAZY)
    private List<QualityControl> qc3QualityControlList;

}
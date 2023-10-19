package ir.pajoohan.mce.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import static ir.pajoohan.mce.entity.WarehouseInput.SCHEMA_MCE;
import static ir.pajoohan.mce.entity.WarehouseInput.TABLE_WAREHOUSE_INPUT;

@Getter
@Setter
@Entity
@Table(name = TABLE_WAREHOUSE_INPUT, schema = SCHEMA_MCE)
public class WarehouseInput {

    public static final String SCHEMA_MCE = "MCE";
    public static final String TABLE_WAREHOUSE_INPUT = "WAREHOUSE_INPUT";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "warehouseInput_generator")
    @SequenceGenerator(name = "warehouseInput_generator", sequenceName = "SQ_WAREHOUSE_INPUT", schema = SCHEMA_MCE, allocationSize = 1)
    private Long id;

    @Size(min = 8, max = 400, message = "WAREHOUSE_INPUT'S BOM_LINK must be 8 characters.")
    @Pattern(regexp = "[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)",
            message = "WAREHOUSE_INPUT'S BOM_LINK_URL must be valid URL.")
    @Column(name = "BOM_LINK_URL")
    String bomLinkUrl;

    @Min(value = 1380, message = "WAREHOUSE_INPUT'S YEAR must be between 1380 to 2050.")
    @Max(value = 2050, message = "WAREHOUSE_INPUT'S YEAR must be between 1380 to 2050.")
    @Column(name = "YEAR")
    Integer year;

    @Size(min = 8, max = 8, message = "WAREHOUSE_INPUT'S KOOTAJ_NUM must be 8 characters.")
    @Pattern(regexp = "^[0-9]\\d*$", message = "WAREHOUSE_INPUT'S KOOTAJ_NUM must be digits only")
    @Column(name = "KOOTAJ_NUM")
    String kootajNum;


    @NotBlank(message = "Enter WAREHOUSE_INPUT'S NUM.")
    @Min(value = 0, message = "WAREHOUSE_INPUT'S NUM must be between 0 to 999999.")
    @Max(value = 999999, message = "WAREHOUSE_INPUT'S NUM must be between 0 to 999999.")
    @Column(name = "NUM", nullable = false)
    private Integer num;

    @NotBlank(message = "Enter WAREHOUSE_INPUT'S ENGINE_NUM_START.")
    @Positive(message = "WAREHOUSE_INPUT'S ENGINE_NUM_START must be positive number.")
    @Digits(integer = 18, fraction = 0, message = "WAREHOUSE_INPUT'S ENGINE_NUM_START must be less than 18 integer digits.")
    @Column(name = "ENGINE_NUM_START")
    private Long engineNumStart;

    @NotBlank(message = "Enter WAREHOUSE_INPUT'S ENGINE_NUM_END.")
    @Positive(message = "WAREHOUSE_INPUT'S ENGINE_NUM_END must be positive number.")
    @Digits(integer = 18, fraction = 0, message = "WAREHOUSE_INPUT'S ENGINE_NUM_END must be less than 18 integer digits.")
    @Column(name = "ENGINE_NUM_END")
    private Long engineNumEnd;

    @NotBlank(message = "Enter WAREHOUSE_INPUT'S INPUT_DATE.")
    @PastOrPresent(message = "WAREHOUSE_INPUT'S INPUT_DATE must be past or present.")
    @Column(name = "INPUT_DATE", nullable = false)
    private Date inputDate;

    @PastOrPresent(message = "WAREHOUSE_INPUT'S CONTROL_DATE must be past or present.")
    @Column(name = "CONTROL_DATE")
    private Date controlDate;

    @PastOrPresent(message = "WAREHOUSE_INPUT'S PROD_PERMISSION_DATE must be past or present.")
    @Column(name = "PROD_PERMISSION_DATE")
    private Date prodPermissionDate;

    @Size(min = 2, max = 10, message = "WAREHOUSE_INPUT'S RECEIPT_NUMBER must be between 2 to 10 characters.")
    @Pattern(regexp = "^[0-9]\\d*$", message = "WAREHOUSE_INPUT'S RECEIPT_NUMBER must be digits only")
    @Column(name = "RECEIPT_NUMBER")
    private String receiptNumber;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTROLLER_PERSN_ID")
    private Person controllerPerson;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDENTIFIERS_ATTACH_ID")
    private Attachment identifiersAttach;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECEIPTS_ATTACH_ID")
    private Attachment receiptsAttach;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REPORTS_ATTACH_ID")
    private Attachment reportsAttach;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROD_PERM_PERSN_ID")
    private Person prodPermissionPerson;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ENGINE_TYPE_ID")
    private EngineType engineType;
}

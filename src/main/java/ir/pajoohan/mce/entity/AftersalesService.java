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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import java.util.Date;

import static ir.pajoohan.mce.entity.AftersalesService.SCHEMA_MCE;
import static ir.pajoohan.mce.entity.AftersalesService.TABLE;

@Getter
@Setter
@Entity
@Table(name = TABLE, schema = SCHEMA_MCE)
@SQLDelete(sql = "UPDATE " + State.TABLE + " SET DISABLE_DATE = TRUNC(CURRENT_DATE) WHERE id = ? and DISABLE_DATE is null")
public class AftersalesService extends Effective {

    public static final String SCHEMA_MCE = "MCE";
    public static final String TABLE = "AFTERSALES_SERVICE";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aftersalesService_generator")
    @SequenceGenerator(name = "aftersalesService_generator", sequenceName = "SQ_" + TABLE, schema = SCHEMA_MCE, allocationSize = 1)
    private Long id;

    @NotBlank(message = "Enter AFTERSALES_SERVICE'S SERVICE_TITLE.")
    @Size(min = 2, max = 200, message = "AFTERSALES_SERVICE'S SERVICE_TITLE must be between 2 to 200 characters.")
    @Column(name = "SERVICE_TITLE", nullable = false, length = 200)
    private String serviceTitle;

    @NotBlank(message = "Enter AFTERSALES_SERVICE'S SERVICE_DESC.")
    @Size(min = 2, max = 2000, message = "AFTERSALES_SERVICE'S SERVICE_DESC must be between 2 to 2000 characters.")
    @Column(name = "SERVICE_DESC", nullable = false, length = 2000)
    private String serviceDesc;

    @NotBlank(message = "Enter AFTERSALES_SERVICE'S SERVICE_DATE.")
    @PastOrPresent(message = "AFTERSALES_SERVICE'S SERVICE_DATE must be past or present.")
    @Column(name = "SERVICE_DATE", nullable = false)
    private Date serviceDate;

    @NotBlank(message = "Enter AFTERSALES_SERVICE'S SERVICE_DATE.")
    @Min(value = 0, message = "AFTERSALES_SERVICE'S SERVICE_DATE must be between 0 to 9999999.")
    @Max(value = 9999999, message = "AFTERSALES_SERVICE'S SERVICE_DATE must be between 0 to 9999999.")
    @Column(name = "KILOMETER", nullable = false)
    private Long kilometer;

    /*----------------------------------------------------------------------------------------------------------------*/

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MOTORCYCLE_ID")
    private Motorcycle motorcycle;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "BRANCH_ID")
    private Branch branch;
}

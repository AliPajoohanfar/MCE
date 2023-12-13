package ir.pajoohan.mce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.pajoohan.mce.entity.baseModel.Effective;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import java.util.Date;
import java.util.List;

import static ir.pajoohan.mce.entity.Motorcycle.SCHEMA_MCE;
import static ir.pajoohan.mce.entity.Motorcycle.TABLE;

@Getter
@Setter
@Entity
@Table(name = TABLE, schema = SCHEMA_MCE)
@SQLDelete(sql = "UPDATE " + TABLE + " SET DISABLE_DATE = TRUNC(CURRENT_DATE) WHERE id = ? and DISABLE_DATE is null")
public class Motorcycle extends Effective {

    public static final String SCHEMA_MCE = "MCE";
    public static final String TABLE = "MOTORCYCLE";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "motorcycle_generator")
    @SequenceGenerator(name = "motorcycle_generator", sequenceName = "SQ_" + TABLE, schema = SCHEMA_MCE, allocationSize = 1)
    private Long id;

    @NotBlank(message = "Enter MOTORCYCLE'S ENGINE_NUM.")
    @Size(min = 20, max = 20, message = "MOTORCYCLE'S ENGINE_NUM must be 20 characters.")
    @Column(name = "ENGINE_NUM", unique = true, nullable = false, length = 20)
    private String engineMum;

    @NotBlank(message = "Enter MOTORCYCLE'S CHASSIS_NUM.")
    @Size(min = 20, max = 20, message = "MOTORCYCLE'S CHASSIS_NUM must be 20 characters.")
    @Column(name = "CHASSIS_NUM", unique = true, nullable = false, length = 20)
    private String chassisNum;

    @Column(name = "NUMBERING_DATE")
    private Date numberingDate;

    @Column(name = "EXIT_DATE")
    private Date exitDate;

    @Size(min = 10, max = 10, message = "MOTORCYCLE'S LICENSE_PLATE must be 10 characters.")
    @Column(name = "LICENSE_PLATE", length = 10)
    private String licensePlate;

    @Size(min = 10, max = 10, message = "MOTORCYCLE'S WAYBILL_NUM must be 10 characters.")
    @Column(name = "WAYBILL_NUM", length = 10)
    private String waybillNum;

    @Column(name = "CUSTOMER_BUY_DATE")
    private Date customerBuyDate;

    /*----------------------------------------------------------------------------------------------------------------*/

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MC_TYPE_ID")
    private MotorcycleType motorcycleType;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRANCH_ID")
    private Branch branch;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WH_INPUT_ID")
    private WarehouseInput warehouseInput;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COLOR_ID")
    private Color color;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATUS_ID")
    private Status status;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATE_ID")
    private State state;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MC_DOCS_ATTACH_ID")
    private Attachment attachment;

    /*----------------------------------------------------------------------------------------------------------------*/

    @JsonIgnore
    @OneToMany(mappedBy = "motorcycle", fetch = FetchType.LAZY)
    private List<AftersalesService> aftersalesServiceList;

    @JsonIgnore
    @OneToOne(mappedBy = "motorcycle", fetch = FetchType.LAZY)
    private FinalControl finalControl;

    @JsonIgnore
    @OneToOne(mappedBy = "motorcycle", fetch = FetchType.LAZY)
    private PreDeliveryControl preDeliveryControl;

    @JsonIgnore
    @OneToOne(mappedBy = "motorcycle", fetch = FetchType.LAZY)
    private QualityControl qualityControl;
}

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
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import java.util.List;

import static ir.pajoohan.mce.entity.Status.SCHEMA_MCE;
import static ir.pajoohan.mce.entity.Status.TABLE;

@Getter
@Setter
@Entity
@Table(name = TABLE, schema = SCHEMA_MCE,
        uniqueConstraints = {
                @UniqueConstraint(name = "STATUS_1_UK", columnNames = {"STATUS_FLOW", "CODE"}),
                @UniqueConstraint(name = "STATUS_2_UK", columnNames = {"STATUS_FLOW", "ORDER_NUM"})})
@SQLDelete(sql = "UPDATE " + State.TABLE + " SET DISABLE_DATE = TRUNC(CURRENT_DATE) WHERE id = ? and DISABLE_DATE is null")
public class Status extends Effective {

    public static final String SCHEMA_MCE = "MCE";
    public static final String TABLE = "STATUS";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "status_generator")
    @SequenceGenerator(name = "status_generator", sequenceName = "SQ_" + TABLE, schema = SCHEMA_MCE, allocationSize = 1)
    private Long id;

    @NotBlank(message = "Enter STATUS'S STATUS_FLOW.")
    @Size(min = 2, max = 4, message = "STATUS'S STATUS_FLOW must be between 2 to 5 characters.")
    @Column(name = "STATUS_FLOW", length = 4, nullable = false)
    private String statusFlow;

    @Min(value = 0, message = "STATUS'S ORDER_NUM must be between 0 to 999.")
    @Max(value = 999, message = "STATUS'S ORDER_NUM must be between 0 to 999.")
    @Column(name = "ORDER_NUM")
    private Integer orderNum;

    @NotBlank(message = "Enter STATUS'S CODE.")
    @Size(min = 2, max = 5, message = "STATUS'S code must be between 2 to 5 characters.")
    @Column(name = "CODE", length = 5, nullable = false)
    private String code;

    @Size(min = 2, max = 10, message = "STATUS'S DESC_PERSIAN must be between 2 to 100 characters.")
    @Column(name = "DESC_PERSIAN", length = 100)
    private String descPersian;

    @Size(min = 2, max = 10, message = "STATUS'S DESC_ENGLISH must be between 2 to 100 characters.")
    @Column(name = "DESC_ENGLISH", length = 100)
    private String descEnglish;

    /*----------------------------------------------------------------------------------------------------------------*/

    @JsonIgnore
    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Motorcycle> motorcycleList;

    @JsonIgnore
    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FinalControl> finalControlList;

    @JsonIgnore
    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PreDeliveryControl> preDeliveryControlList;

    @JsonIgnore
    @OneToMany(mappedBy = "qc1Status", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<QualityControl> qc1QualityControlList;

    @JsonIgnore
    @OneToMany(mappedBy = "qc2Status", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<QualityControl> qc2QualityControlList;

    @JsonIgnore
    @OneToMany(mappedBy = "qc3Status", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<QualityControl> qc3QualityControlList;

}

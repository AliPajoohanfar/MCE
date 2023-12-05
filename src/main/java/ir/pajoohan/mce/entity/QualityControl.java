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
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import static ir.pajoohan.mce.entity.QualityControl.SCHEMA_MCE;
import static ir.pajoohan.mce.entity.QualityControl.TABLE;

@Getter
@Setter
@Entity
@Table(name = TABLE, schema = SCHEMA_MCE)
@SQLDelete(sql = "UPDATE " + State.TABLE + " SET DISABLE_DATE = TRUNC(CURRENT_DATE) WHERE id = ? and DISABLE_DATE is null")
public class QualityControl extends Effective {

    public static final String SCHEMA_MCE = "MCE";
    public static final String TABLE = "QUALITY_CONTROL";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "preDeliveryControl_generator")
    @SequenceGenerator(name = "preDeliveryControl_generator", sequenceName = "SQ_" + TABLE, schema = SCHEMA_MCE, allocationSize = 1)
    private Long id;

    @NotBlank(message = "Enter QUALITY_CONTROL'S QC_1_COMMENT.")
    @Size(min = 2, max = 2000, message = "QUALITY_CONTROL'S QC_1_COMMENT must be 2000 characters.")
    @Column(name = "QC_1_COMMENT", length = 2000)
    private String qc1Comment;

    @NotBlank(message = "Enter QUALITY_CONTROL'S QC_2_COMMENT.")
    @Size(min = 2, max = 2000, message = "QUALITY_CONTROL'S QC_2_COMMENT must be 2000 characters.")
    @Column(name = "QC_2_COMMENT", length = 2000)
    private String qc2Comment;

    @NotBlank(message = "Enter QUALITY_CONTROL'S QC_3_COMMENT.")
    @Size(min = 2, max = 2000, message = "QUALITY_CONTROL'S QC_3_COMMENT must be 2000 characters.")
    @Column(name = "QC_3_COMMENT", length = 2000)
    private String qc3Comment;

    /*----------------------------------------------------------------------------------------------------------------*/

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "QC_1_PERSON_ID")
    private Person qc1Person;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "QC_1_STATUS_ID")
    private Status qc1Status;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "QC_2_PERSON_ID")
    private Person qc2Person;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "QC_2_STATUS_ID")
    private Status qc2Status;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "QC_3_PERSON_ID")
    private Person qc3Person;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "QC_3_STATUS_ID")
    private Status qc3Status;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MOTORCYCLE_ID")
    private Motorcycle motorcycle;

}

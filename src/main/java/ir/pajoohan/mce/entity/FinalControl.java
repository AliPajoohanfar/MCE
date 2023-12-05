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

import static ir.pajoohan.mce.entity.FinalControl.SCHEMA_MCE;
import static ir.pajoohan.mce.entity.FinalControl.TABLE;

@Getter
@Setter
@Entity
@Table(name = TABLE, schema = SCHEMA_MCE)
@SQLDelete(sql = "UPDATE " + State.TABLE + " SET DISABLE_DATE = TRUNC(CURRENT_DATE) WHERE id = ? and DISABLE_DATE is null")
public class FinalControl extends Effective {

    public static final String SCHEMA_MCE = "MCE";
    public static final String TABLE = "FINAL_CONTROL";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "finalControl_generator")
    @SequenceGenerator(name = "finalControl_generator", sequenceName = "SQ_" + TABLE, schema = SCHEMA_MCE, allocationSize = 1)
    private Long id;

    @NotBlank(message = "Enter FINAL_CONTROL'S FAILURE_TITLE.")
    @Size(min = 2, max = 200, message = "FINAL_CONTROL'S FAILURE_TITLE must be 200 characters.")
    @Column(name = "FAILURE_TITLE", length = 200)
    private String failureTitle;

    @NotBlank(message = "Enter FINAL_CONTROL'S FAILURE_DESC.")
    @Size(min = 2, max = 2000, message = "FINAL_CONTROL'S FAILURE_DESC must be 2000 characters.")
    @Column(name = "FAILURE_DESC", length = 2000)
    private String failureDesc;

    @NotBlank(message = "Enter FINAL_CONTROL'S CORRECTIVE_ACTION.")
    @Size(min = 2, max = 2000, message = "FINAL_CONTROL'S CORRECTIVE_ACTION must be 2000 characters.")
    @Column(name = "CORRECTIVE_ACTION", length = 2000)
    private String correctiveAction;

    @NotBlank(message = "Enter FINAL_CONTROL'S DESCRIPTION.")
    @Size(min = 2, max = 2000, message = "FINAL_CONTROL'S DESCRIPTION must be 2000 characters.")
    @Column(name = "DESCRIPTION", length = 2000)
    private String description;

    /*----------------------------------------------------------------------------------------------------------------*/

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "STATUS_ID")
    private Status status;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MOTORCYCLE_ID")
    private Motorcycle motorcycle;

}
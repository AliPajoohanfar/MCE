package ir.pajoohan.mce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

import static ir.pajoohan.mce.entity.PreDeliveryControl.SCHEMA_MCE;
import static ir.pajoohan.mce.entity.PreDeliveryControl.TABLE_PRE_DELIVERY_CONTROL;

@Getter
@Setter
@Entity
@Table(name = TABLE_PRE_DELIVERY_CONTROL, schema = SCHEMA_MCE)
public class PreDeliveryControl {

    public static final String SCHEMA_MCE = "MCE";
    public static final String TABLE_PRE_DELIVERY_CONTROL = "PRE_DELIVERY_CONTROL";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "preDeliveryControl_generator")
    @SequenceGenerator(name = "preDeliveryControl_generator", sequenceName = "SQ_PRE_DELIVERY_CONTROL", schema = SCHEMA_MCE, allocationSize = 1)
    private Long id;

    @NotBlank(message = "Enter PRE_DELIVERY_CONTROL'S FAILURE_DESC.")
    @Size(min = 2, max = 2000, message = "PRE_DELIVERY_CONTROL'S FAILURE_DESC must be 2000 characters.")
    @Column(name = "FAILURE_DESC", length = 2000)
    private String failureDesc;

    @NotBlank(message = "Enter PRE_DELIVERY_CONTROL'S CORRECTIVE_ACTION.")
    @Size(min = 2, max = 2000, message = "PRE_DELIVERY_CONTROL'S CORRECTIVE_ACTION must be 2000 characters.")
    @Column(name = "CORRECTIVE_ACTION", length = 2000)
    private String correctiveAction;

    @NotBlank(message = "Enter PRE_DELIVERY_CONTROL'S DESCRIPTION.")
    @Size(min = 2, max = 2000, message = "PRE_DELIVERY_CONTROL'S DESCRIPTION must be 2000 characters.")
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
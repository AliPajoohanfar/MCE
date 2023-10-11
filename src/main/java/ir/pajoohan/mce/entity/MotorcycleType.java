package ir.pajoohan.mce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import static ir.pajoohan.mce.entity.MotorcycleType.SCHEMA_MCE;
import static ir.pajoohan.mce.entity.MotorcycleType.TABLE_MOTORCYCLE_TYPE;

@Getter
@Setter
@Entity
@Table(name = TABLE_MOTORCYCLE_TYPE, schema = SCHEMA_MCE)
public class MotorcycleType {

    public static final String SCHEMA_MCE = "MCE";
    public static final String TABLE_MOTORCYCLE_TYPE = "MOTORCYCLE_TYPE";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "motorcycleType_generator")
    @SequenceGenerator(name = "motorcycleType_generator", sequenceName = "SQ_MOTORCYCLE_TYPE", schema = SCHEMA_MCE, allocationSize = 1)
    private Long id;

    @NotBlank(message = "Enter MOTORCYCLE_TYPE'S CODE.")
    @Size(min = 2, max = 10, message = "MOTORCYCLE_TYPE'S CODE must be between 2 to 20 characters.")
    @Column(name = "CODE", nullable = false, length = 20, unique = true)
    private String code;

    @NotBlank(message = "Enter MOTORCYCLE_TYPE'S NAME.")
    @Size(min = 2, max = 100, message = "MOTORCYCLE_TYPE'S NAME must be between 2 to 200 characters.")
    @Column(name = "NAME", nullable = false, length = 200)
    private String name;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INFORMATION_ATTACH_ID")
    private Attachment attachment;
}

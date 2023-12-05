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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import java.util.List;

import static ir.pajoohan.mce.entity.MotorcycleType.SCHEMA_MCE;
import static ir.pajoohan.mce.entity.MotorcycleType.TABLE;

@Getter
@Setter
@Entity
@Table(name = TABLE, schema = SCHEMA_MCE)
@SQLDelete(sql = "UPDATE " + TABLE + " SET DISABLE_DATE = TRUNC(CURRENT_DATE) WHERE id = ? and DISABLE_DATE is null")
public class MotorcycleType extends Effective {

    public static final String SCHEMA_MCE = "MCE";
    public static final String TABLE = "MOTORCYCLE_TYPE";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "motorcycleType_generator")
    @SequenceGenerator(name = "motorcycleType_generator", sequenceName = "SQ_" + TABLE, schema = SCHEMA_MCE, allocationSize = 1)
    private Long id;

    @NotBlank(message = "Enter MOTORCYCLE_TYPE'S CODE.")
    @Size(min = 2, max = 10, message = "MOTORCYCLE_TYPE'S CODE must be between 2 to 20 characters.")
    @Column(name = "CODE", nullable = false, length = 20, unique = true)
    private String code;

    @NotBlank(message = "Enter MOTORCYCLE_TYPE'S NAME.")
    @Size(min = 2, max = 100, message = "MOTORCYCLE_TYPE'S NAME must be between 2 to 200 characters.")
    @Column(name = "NAME", nullable = false, length = 200)
    private String name;

    /*----------------------------------------------------------------------------------------------------------------*/

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INFORMATION_ATTACH_ID")
    private Attachment attachment;

    /*----------------------------------------------------------------------------------------------------------------*/

    @JsonIgnore
    @OneToMany(mappedBy = "motorcycleType", fetch = FetchType.LAZY)
    private List<Motorcycle> motorcycleList;

}

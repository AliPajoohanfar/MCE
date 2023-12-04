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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import java.util.List;

import static ir.pajoohan.mce.entity.EngineType.SCHEMA_MCE;
import static ir.pajoohan.mce.entity.EngineType.TABLE;

@Getter
@Setter
@Entity
@Table(name = TABLE, schema = SCHEMA_MCE)
@SQLDelete(sql = "UPDATE " + State.TABLE + " SET DISABLE_DATE = TRUNC(CURRENT_DATE) WHERE id = ?")
public class EngineType extends Effective {

    public static final String SCHEMA_MCE = "MCE";
    public static final String TABLE = "ENGINE_TYPE";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "engineType_generator")
    @SequenceGenerator(name = "engineType_generator", sequenceName = "SQ_" + TABLE, schema = SCHEMA_MCE, allocationSize = 1)
    private Long id;

    @NotBlank(message = "Enter ENGINE_TYPE'S CODE.")
    @Size(min = 2, max = 20, message = "ENGINE_TYPE'S CODE must be between 2 to 20 characters.")
    @Column(name = "CODE", unique = true, length = 20, nullable = false)
    private String code;

    @Size(min = 2, max = 200, message = "ENGINE_TYPE'S NAME must be between 2 to 200 characters.")
    @Column(name = "NAME", length = 200)
    private String name;

    @Min(value = 0, message = "ENGINE_TYPE'S VOLUME must be between 0 to 9999.")
    @Max(value = 9999, message = "ENGINE_TYPE'S VOLUME must be between 0 to 9999.")
    @Column(name = "VOLUME")
    private Integer volume;

    @Min(value = 0, message = "ENGINE_TYPE'S POWER must be between 0 to 9999.")
    @Max(value = 9999, message = "ENGINE_TYPE'S POWER must be between 0 to 9999.")
    @Column(name = "POWER")
    private Integer power;

    @Min(value = 0, message = "ENGINE_TYPE'S FUEL must be between 0 to 1.")
    @Max(value = 1, message = "ENGINE_TYPE'S FUEL must be between 0 to 1.")
    @Column(name = "FUEL")
    private Integer fuel;

    /*----------------------------------------------------------------------------------------------------------------*/

    @JsonIgnore
    @OneToMany(mappedBy = "engineType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<WarehouseInput> warehouseInputList;

}

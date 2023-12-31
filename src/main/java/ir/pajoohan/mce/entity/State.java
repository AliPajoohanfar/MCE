package ir.pajoohan.mce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.pajoohan.mce.entity.baseModel.Effective;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import java.util.List;

import static ir.pajoohan.mce.entity.State.SCHEMA_MCE;
import static ir.pajoohan.mce.entity.State.TABLE;

@Getter
@Setter
@Entity
@Table(name = TABLE, schema = SCHEMA_MCE)
@SQLDelete(sql = "UPDATE " + TABLE + " SET DISABLE_DATE = TRUNC(CURRENT_DATE) WHERE id = ? and DISABLE_DATE is null")
public class State extends Effective {

    public static final String SCHEMA_MCE = "MCE";
    public static final String TABLE = "STATE";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "state_generator")
    @SequenceGenerator(name = "state_generator", sequenceName = "SQ_" + TABLE, schema = SCHEMA_MCE, allocationSize = 1)
    private Long id;

    @NotBlank(message = "Enter STATE'S NAME.")
    @Size(min = 2, max = 100, message = "STATE'S NAME must be between 2 to 100 characters.")
    @Column(name = "NAME", unique = true, length = 100, nullable = false)
    private String name;

    @NotBlank(message = "Enter STATE'S TEL_CODE.")
    @Size(min = 2, max = 3, message = "STATE'S TEL_CODE must be between 2 to 3 characters.")
    @Pattern(regexp = "^[0-9]\\d*$", message = "State'S TEL_CODE must be digits only")
    @Column(name = "TEL_CODE", length = 3, nullable = false)
    private String telCode;

    /*----------------------------------------------------------------------------------------------------------------*/

    @JsonIgnore
    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY)
    private List<Branch> branchList;

    @JsonIgnore
    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY)
    private List<Motorcycle> motorcycleList;
}


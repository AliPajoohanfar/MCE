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
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import java.util.List;

import static ir.pajoohan.mce.entity.Branch.SCHEMA_MCE;
import static ir.pajoohan.mce.entity.Branch.TABLE;

@Getter
@Setter
@Entity
@Table(name = TABLE, schema = SCHEMA_MCE)
@SQLDelete(sql = "UPDATE " + TABLE + " SET DISABLE_DATE = TRUNC(CURRENT_DATE) WHERE id = ? and DISABLE_DATE is null")
public class Branch extends Effective {

    public static final String SCHEMA_MCE = "MCE";
    public static final String TABLE = "BRANCH";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "branch_generator")
    @SequenceGenerator(name = "branch_generator", sequenceName = "SQ_" + TABLE, schema = SCHEMA_MCE, allocationSize = 1)
    private Long id;

    @NotBlank(message = "Enter BRANCH'S CODE.")
    @Size(min = 2, max = 10, message = "BRANCH'S CODE must be between 2 to 10 characters.")
    @Column(name = "CODE", nullable = false, length = 10, unique = true)
    private String code;

    @NotBlank(message = "Enter BRANCH'S NAME.")
    @Size(min = 2, max = 100, message = "BRANCH'S NAME must be between 2 to 100 characters.")
    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @NotNull(message = "Enter BRANCH'S MC_DOC_PRINT_PERMISSION.")
    @Column(name = "MC_DOC_PRINT_PERMISSION", nullable = false)
    private Boolean mcDocPrintPermission;

    @NotNull(message = "Enter BRANCH'S BRANCH_TYP.")
    @Min(value = 0, message = "BRANCH'S BRANCH_TYP must be between 0 to 1.")
    @Max(value = 1, message = "BRANCH'S BRANCH_TYP must be between 0 to 1.")
    @Column(name = "BRANCH_TYP", nullable = false)
    private Integer branchTyp;

    @Email(message = "Enter valid BRANCH'S EMAIL")
    @Column(name = "EMAIL", length = 250)
    private String email;
    /*----------------------------------------------------------------------------------------------------------------*/

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Branch parent;

    @JsonIgnore
    @NotNull(message = "Enter BRANCH'S STATE_ID.")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "STATE_ID", nullable = false)
    private State state;

    @JsonIgnore
    @NotNull(message = "Enter BRANCH'S PERSON_ID.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_ID", nullable = false)
    private Person person;

    /*----------------------------------------------------------------------------------------------------------------*/

    @JsonIgnore
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<Branch> branchList;

    @JsonIgnore
    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY)
    private List<Motorcycle> branchMotorcycleList;

    @JsonIgnore
    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY)
    private List<AftersalesService> aftersalesServiceList;
}

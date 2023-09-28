package ir.pajoohan.mce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import static ir.pajoohan.mce.entity.Branch.SCHEMA_MCE;
import static ir.pajoohan.mce.entity.Branch.TABLE_BRANCH;

@Getter
@Setter
@Entity
@Table(name = TABLE_BRANCH, schema = SCHEMA_MCE)
public class Branch {

    public static final String SCHEMA_MCE = "MCE";
    public static final String TABLE_BRANCH = "BRANCH";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "branch_generator")
    @SequenceGenerator(name = "branch_generator", sequenceName = "SQ_BRANCH", schema = SCHEMA_MCE, allocationSize = 1)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Branch parent;

    @NotBlank(message = "Enter BRANCH'S CODE.")
    @Size(min = 2, max = 10, message = "BRANCH'S CODE must between 2 to 10 characters.")
    @Column(name = "CODE", nullable = false, length = 10, unique = true)
    private String code;

    @NotBlank(message = "Enter BRANCH'S NAME.")
    @Size(min = 2, max = 100, message = "BRANCH'S NAME must between 2 to 100 characters.")
    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @JsonIgnore
    @NotBlank(message = "Enter BRANCH'S STATE_ID.")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "STATE_ID", nullable = false)
    private State state;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    @NotBlank(message = "Enter BRANCH'S MC_DOC_PRINT_PERMISSION.")
    @Column(name = "MC_DOC_PRINT_PERMISSION", nullable = false)
    private Boolean mcDocPrintPermission;

    @NotBlank(message = "Enter BRANCH'S BRANCH_TYP.")
    @Column(name = "BRANCH_TYP", nullable = false)
    private Integer branchTyp;

}

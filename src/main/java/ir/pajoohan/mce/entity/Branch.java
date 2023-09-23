package ir.pajoohan.mce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import static ir.pajoohan.mce.entity.Branch.TABLE_BRANCH;
import static ir.pajoohan.mce.entity.State.SCHEMA_MCE;

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
    @SequenceGenerator(name = "branch_generator", sequenceName = "sq_branch", schema = SCHEMA_MCE, allocationSize = 1)
    private Long id;

    //TODO FK
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Branch parent;

    @Column(name = "CODE", unique = true)
    @Size(min = 2, max = 10, message = "branch code must between 2 to 10 characters.")
    private String code;

    @Column(name = "NAME")
    @Size(min = 2, max = 100, message = "branch name must between 2 to 100 characters.")
    private String name;

    //TODO FK
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "STATE_ID", nullable = false)
    private State state;

    //TODO FK
    @Column(name = "PERSON_ID")
    private Long personId;

    @Column(name = "MC_DOC_PRINT_PERMISSION")
    private Boolean McDocPrintPermission;

    @Column(name = "BRANCH_TYP")
    private Integer branchTyp;

}

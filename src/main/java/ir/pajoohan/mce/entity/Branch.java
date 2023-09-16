package ir.pajoohan.mce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    @Column(name = "PARENT_ID")
    private Long parentId;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    //TODO FK
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "STATE_ID", nullable = false)
    private State state;

    @Column(name = "MC_DOC_PRINT_PERMISSION")
    private Boolean McDocPrintPermission;

    @Column(name = "BRANCH_TYP")
    private Integer branchTyp;
}

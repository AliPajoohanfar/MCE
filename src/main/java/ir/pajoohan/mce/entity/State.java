package ir.pajoohan.mce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static ir.pajoohan.mce.entity.State.SCHEMA_MCE;
import static ir.pajoohan.mce.entity.State.TABLE_STATE;

@Getter
@Setter
@Entity
@Table(name = TABLE_STATE, schema = SCHEMA_MCE)
public class State {

    public static final String SCHEMA_MCE = "MCE";
    public static final String TABLE_STATE = "STATE";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "state_generator")
    @SequenceGenerator(name = "state_generator", sequenceName = "SQ_STATE", schema = SCHEMA_MCE, allocationSize = 1)
    private Long id;

    @NotBlank(message = "Enter STATE'S NAME.")
    @Size(min = 2, max = 100, message = "STATE'S NAME must be between 2 to 100 characters.")
    @Column(name = "NAME", unique = true, length = 100, nullable = false)
    private String name;

    @NotBlank(message = "Enter STATE'S TEL_CODE.")
    @Size(min = 2, max = 3, message = "STATE'S TEL_CODE must be between 2 to 3 characters.")
    @Column(name = "TEL_CODE", length = 3, nullable = false)
    private String telCode;

    /*----------------------------------------------------------------------------------------------------------------*/

    @JsonIgnore
    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Branch> branchList;
}


package ir.pajoohan.mce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.pajoohan.mce.entity.baseModel.Auditable;
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
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static ir.pajoohan.mce.entity.Color.SCHEMA_MCE;
import static ir.pajoohan.mce.entity.Color.TABLE;

@Getter
@Setter
@Entity
@Table(name = TABLE, schema = SCHEMA_MCE,
        uniqueConstraints = {@UniqueConstraint(name = "COLOR_2_UK", columnNames = {"R", "G", "B"})})
public class Color extends Auditable<String> {

    public static final String SCHEMA_MCE = "MCE";
    public static final String TABLE = "COLOR";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "color_generator")
    @SequenceGenerator(name = "color_generator", sequenceName = "SQ_" + TABLE, schema = SCHEMA_MCE, allocationSize = 1)
    private Long id;

    @Size(min = 2, max = 10, message = "COLOR'S CODE must be between 2 to 10 characters.")
    @Column(name = "CODE", unique = true, length = 10)
    private String code;

    @NotBlank(message = "Enter COLOR'S PERSIAN_NAME.")
    @Size(min = 2, max = 100, message = "COLOR'S PERSIAN_NAME must be between 2 to 100 characters.")
    @Column(name = "PERSIAN_NAME", length = 100, nullable = false)
    private String persianName;

    @Size(min = 2, max = 100, message = "COLOR'S ENGLISH_NAME must be between 2 to 100 characters.")
    @Column(name = "ENGLISH_NAME", length = 100)
    private String englishName;

    @Min(value = 0, message = "COLOR'S R must be between 0 to 255.")
    @Max(value = 255, message = "COLOR'S R must be between 0 to 255.")
    @Column(name = "R")
    private Integer r;

    @Min(value = 0, message = "COLOR'S G must be between 0 to 255.")
    @Max(value = 255, message = "COLOR'S G must be between 0 to 255.")
    @Column(name = "G")
    private Integer g;

    @Min(value = 0, message = "COLOR'S B must be between 0 to 255.")
    @Max(value = 255, message = "COLOR'S B must be between 0 to 255.")
    @Column(name = "B")
    private Integer b;

    /*----------------------------------------------------------------------------------------------------------------*/

    @JsonIgnore
    @OneToMany(mappedBy = "color", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Motorcycle> motorcycleList;

}

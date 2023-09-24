package ir.pajoohan.mce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import static ir.pajoohan.mce.entity.Color.SCHEMA_MCE;
import static ir.pajoohan.mce.entity.Color.TABLE_COLOR;

@Getter
@Setter
@Entity
@Table(name = TABLE_COLOR, schema = SCHEMA_MCE,
        uniqueConstraints = {@UniqueConstraint(name = "COLOR_2_UK", columnNames = {"r", "g", "b"})})
public class Color {

    public static final String SCHEMA_MCE = "MCE";
    public static final String TABLE_COLOR = "COLOR";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "color_generator")
    @SequenceGenerator(name = "color_generator", sequenceName = "sq_color", schema = SCHEMA_MCE, allocationSize = 1)
    private Long id;

    @Size(min = 2, max = 10, message = "COLOR'S CODE must between 2 to 10 characters.")
    @Column(name = "CODE", unique = true, length = 10)
    private String code;

    @NotBlank(message = "Enter COLOR'S PERSIAN_NAME.")
    @Size(min = 2, max = 100, message = "COLOR'S PERSIAN_NAME must between 2 to 100 characters.")
    @Column(name = "PERSIAN_NAME", length = 100, nullable = false)
    private String persianName;

    @Size(min = 2, max = 100, message = "COLOR'S ENGLISH_NAME must between 2 to 100 characters.")
    @Column(name = "ENGLISH_NAME", length = 100)
    private String englishName;

    @Column(name = "R")
    private Integer r;

    @Column(name = "G")
    private Integer g;

    @Column(name = "B")
    private Integer b;

}

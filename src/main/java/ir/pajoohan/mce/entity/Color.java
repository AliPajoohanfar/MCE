package ir.pajoohan.mce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import static ir.pajoohan.mce.entity.State.SCHEMA_MCE;
import static ir.pajoohan.mce.entity.State.TABLE_STATE;

@Getter
@Setter
@Entity
@Table(name = TABLE_STATE, schema = SCHEMA_MCE)
public class Color {

    public static final String SCHEMA_MCE = "MCE";
    public static final String TABLE_STATE = "COLOR";


    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "color_generator")
    @SequenceGenerator(name = "color_generator", sequenceName = "sq_color", schema = SCHEMA_MCE, allocationSize = 1)
    private Long id;

    @Size(min = 2, max = 10, message = "color code must between 2 to 10 characters.")
    @Column(name = "CODE")
    String code;

    @Size(min = 2, max = 100, message = "color persianName must between 2 to 100 characters.")
    @Column(name = "PERSIAN_NAME")
    String persianName;

    @Size(min = 2, max = 100, message = "color englishName must between 2 to 100 characters.")
    @Column(name = "ENGLISH_NAME")
    String englishName;

    @Column(name = "R")
    Integer r;

    @Column(name = "G")
    Integer g;

    @Column(name = "B")
    Integer b;

}

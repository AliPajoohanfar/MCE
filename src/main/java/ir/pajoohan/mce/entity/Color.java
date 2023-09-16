package ir.pajoohan.mce.entity;

import jakarta.persistence.*;
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

}

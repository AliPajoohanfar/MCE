package ir.pajoohan.mce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import static ir.pajoohan.mce.entity.State.SCHEMA_MCE;

@Setter
@Getter
@Entity
@Component
@Table(name = "STATE", schema = SCHEMA_MCE)
public class State {

    public static final String SCHEMA_MCE = "MCE";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "state_generator")
    @SequenceGenerator(name = "state_generator", sequenceName = "sq_state", schema = SCHEMA_MCE, allocationSize = 1)
    private Long id;

    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "TEL_CODE", length = 3)
    private String telCode;
}


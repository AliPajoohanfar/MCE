package ir.pajoohan.mce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import static ir.pajoohan.mce.entity.Status.SCHEMA_MCE;
import static ir.pajoohan.mce.entity.Status.TABLE_STATUS;

@Getter
@Setter
@Entity
@Table(name = TABLE_STATUS, schema = SCHEMA_MCE,
        uniqueConstraints = {
                @UniqueConstraint(name = "STATUS_1_UK", columnNames = {"STATUS_FLOW", "CODE"}),
                @UniqueConstraint(name = "STATUS_2_UK", columnNames = {"STATUS_FLOW", "ORDER_NUM"})})

public class Status {

    public static final String SCHEMA_MCE = "MCE";
    public static final String TABLE_STATUS = "STATUS";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "status_generator")
    @SequenceGenerator(name = "status_generator", sequenceName = "SQ_STATUS", schema = SCHEMA_MCE, allocationSize = 1)
    private Long id;

    @NotBlank(message = "Enter STATUS'S STATUS_FLOW.")
    @Size(min = 2, max = 4, message = "STATUS'S STATUS_FLOW must between 2 to 5 characters.")
    @Column(name = "STATUS_FLOW", length = 4, nullable = false)
    private String statusFlow;

    @Min(value = 0, message = "STATUS'S ORDER_NUM must be between 0 to 999.")
    @Max(value = 999, message = "STATUS'S ORDER_NUM must be between 0 to 999.")
    @Column(name = "ORDER_NUM")
    private Integer orderNum;

    @NotBlank(message = "Enter STATUS'S CODE.")
    @Size(min = 2, max = 5, message = "STATUS'S code must between 2 to 5 characters.")
    @Column(name = "CODE", length = 5, nullable = false)
    private String code;

    @Size(min = 2, max = 10, message = "STATUS'S DESC_PERSIAN must between 2 to 100 characters.")
    @Column(name = "DESC_PERSIAN", length = 100)
    private String descPersian;

    @Size(min = 2, max = 10, message = "STATUS'S DESC_ENGLISH must between 2 to 100 characters.")
    @Column(name = "DESC_ENGLISH", length = 100)
    private String descEnglish;

}

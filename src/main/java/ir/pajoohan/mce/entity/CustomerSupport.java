package ir.pajoohan.mce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.pajoohan.mce.entity.baseModel.Effective;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import static ir.pajoohan.mce.entity.CustomerSupport.SCHEMA_MCE;
import static ir.pajoohan.mce.entity.CustomerSupport.TABLE;

@Getter
@Setter
@Entity
@Table(name = TABLE, schema = SCHEMA_MCE)
@SQLDelete(sql = "UPDATE " + State.TABLE + " SET DISABLE_DATE = TRUNC(CURRENT_DATE) WHERE id = ? and DISABLE_DATE is null")
public class CustomerSupport extends Effective {

    public static final String SCHEMA_MCE = "MCE";
    public static final String TABLE = "CUSTOMER_SUPPORT";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerSupport_generator")
    @SequenceGenerator(name = "customerSupport_generator", sequenceName = "SQ_" + TABLE, schema = SCHEMA_MCE, allocationSize = 1)
    private Long id;

    @Column(name = "CUSTOMER_CRITICISM")
    @Size(min = 2, max = 2000, message = "CUSTOMER_SUPPORT'S CUSTOMER_CRITICISM must be between 2 to 2000 characters.")
    private String customerCriticism;

    @Column(name = "CUSTOMER_SUGGESTION")
    @Size(min = 2, max = 2000, message = "CUSTOMER_SUPPORT'S CUSTOMER_SUGGESTION must be between 2 to 2000 characters.")
    private String customerSuggestion;

    @Column(name = "CUSTOMER_COMPLAINT")
    @Size(min = 2, max = 2000, message = "CUSTOMER_SUPPORT'S CUSTOMER_COMPLAINT must be between 2 to 2000 characters.")
    private String customerComplaint;

    @Column(name = "RESPONSE")
    @Size(min = 2, max = 2000, message = "CUSTOMER_SUPPORT'S RESPONSE must be between 2 to 2000 characters.")
    private String response;

    @Column(name = "CUSTOMER_POL")
    @Size(min = 2, max = 2000, message = "CUSTOMER_SUPPORT'S CUSTOMER_POL must be between 2 to 2000 characters.")
    private String customerPol;

    /*----------------------------------------------------------------------------------------------------------------*/

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private CustomerSupport parent;

    /*----------------------------------------------------------------------------------------------------------------*/

    @JsonIgnore
    @OneToOne(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private CustomerSupport parenCustomerSupport;

}


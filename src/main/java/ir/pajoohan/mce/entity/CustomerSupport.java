package ir.pajoohan.mce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

import static ir.pajoohan.mce.entity.CustomerSupport.SCHEMA_MCE;
import static ir.pajoohan.mce.entity.CustomerSupport.TABLE_CUSTOMER_SUPPORT;

@Getter
@Setter
@Entity
@Table(name = TABLE_CUSTOMER_SUPPORT, schema = SCHEMA_MCE)
public class CustomerSupport {

    public static final String SCHEMA_MCE = "MCE";
    public static final String TABLE_CUSTOMER_SUPPORT = "CUSTOMER_SUPPORT";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerSupport_generator")
    @SequenceGenerator(name = "customerSupport_generator", sequenceName = "SQ_CUSTOMER_SUPPORT", schema = SCHEMA_MCE, allocationSize = 1)
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


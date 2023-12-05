package ir.pajoohan.mce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.pajoohan.mce.entity.baseModel.Effective;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import java.sql.Blob;

import static ir.pajoohan.mce.entity.Attachment.SCHEMA_MCE;
import static ir.pajoohan.mce.entity.Attachment.TABLE;

@Entity
@Setter
@Getter
@Table(name = TABLE, schema = SCHEMA_MCE)
@SQLDelete(sql = "UPDATE " + State.TABLE + " SET DISABLE_DATE = TRUNC(CURRENT_DATE) WHERE id = ? and DISABLE_DATE is null")
public class Attachment extends Effective {

    public static final String SCHEMA_MCE = "MCE";
    public static final String TABLE = "ATTACHMENT";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attachment_generator")
    @SequenceGenerator(name = "attachment_generator", sequenceName = "SQ_" + TABLE, schema = SCHEMA_MCE, allocationSize = 1)
    private Long id;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ATTACH_FILE_1", nullable = false)
    private Blob attachFile1;

    @Column(name = "ATTACH_DESC_1")
    @Size(max = 200, message = "ATTACHMENT'S ATTACH_DESCs must be les than 200 characters.")
    private String attachDesc1;


    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ATTACH_FILE_2", nullable = false)
    private Blob attachFile2;

    @Column(name = "ATTACH_DESC_2")
    @Size(max = 200, message = "ATTACHMENT'S ATTACH_DESCs must be les than 200 characters.")
    private String attachDesc2;


    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ATTACH_FILE_3", nullable = false)
    private Blob attachFile3;

    @Column(name = "ATTACH_DESC_3")
    @Size(max = 200, message = "ATTACHMENT'S ATTACH_DESCs must be les than 200 characters.")
    private String attachDesc3;


    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ATTACH_FILE_4", nullable = false)
    private Blob attachFile4;

    @Column(name = "ATTACH_DESC_4")
    @Size(max = 200, message = "ATTACHMENT'S ATTACH_DESCs must be les than 200 characters.")
    private String attachDesc4;


    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ATTACH_FILE_5", nullable = false)
    private Blob attachFile5;

    @Column(name = "ATTACH_DESC_5")
    @Size(max = 200, message = "ATTACHMENT'S ATTACH_DESCs must be les than 200 characters.")
    private String attachDesc5;


    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ATTACH_FILE_6", nullable = false)
    private Blob attachFile6;

    @Column(name = "ATTACH_DESC_6) ")
    @Size(max = 200, message = "ATTACHMENT'S ATTACH_DESCs must be les than 200 characters.")
    private String attachDesc6;


    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ATTACH_FILE_7", nullable = false)
    private Blob attachFile7;

    @Column(name = "ATTACH_DESC_7")
    @Size(max = 200, message = "ATTACHMENT'S ATTACH_DESCs must be les than 200 characters.")
    private String attachDesc7;


    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ATTACH_FILE_8", nullable = false)
    private Blob attachFile8;

    @Column(name = "ATTACH_DESC_8")
    @Size(max = 200, message = "ATTACHMENT'S ATTACH_DESCs must be les than 200 characters.")
    private String attachDesc8;


    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ATTACH_FILE_9", nullable = false)
    private Blob attachFile9;

    @Column(name = "ATTACH_DESC_9")
    @Size(max = 200, message = "ATTACHMENT'S ATTACH_DESCs must be les than 200 characters.")
    private String attachDesc9;


    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ATTACH_FILE_10", nullable = false)
    private Blob attachFile10;

    @Column(name = "ATTACH_DESC_10")
    @Size(max = 200, message = "ATTACHMENT'S ATTACH_DESCs must be les than 200 characters.")
    private String attachDesc10;

    /*----------------------------------------------------------------------------------------------------------------*/

    @JsonIgnore
    @OneToOne(mappedBy = "attachment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private MotorcycleType motorcycleType;

    @JsonIgnore
    @OneToOne(mappedBy = "identifiersAttach", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private WarehouseInput warehouseInputsIdentifiers;

    @JsonIgnore
    @OneToOne(mappedBy = "receiptsAttach", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private WarehouseInput warehouseInputsReceipts;

    @JsonIgnore
    @OneToOne(mappedBy = "reportsAttach", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private WarehouseInput warehouseInputsReports;

    @JsonIgnore
    @OneToOne(mappedBy = "attachment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private MotorcycleType motorcycle;
}

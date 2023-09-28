package ir.pajoohan.mce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;

import static ir.pajoohan.mce.entity.Attachment.SCHEMA_MCE;
import static ir.pajoohan.mce.entity.Attachment.TABLE_ATTACHMENT;

@Entity
@Setter
@Getter
@Table(name = TABLE_ATTACHMENT, schema = SCHEMA_MCE)
public class Attachment {

    public static final String SCHEMA_MCE = "MCE";
    public static final String TABLE_ATTACHMENT = "ATTACHMENT";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attachment_generator")
    @SequenceGenerator(name = "attachment_generator", sequenceName = "SQ_ATTACHMENT", schema = SCHEMA_MCE, allocationSize = 1)
    private Long id;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ATTACH_FILE_1", nullable = false)
    private Blob attachFile1;

    @Column(name = "ATTACH_DESC_1")
    @Size(max = 200, message = "ATTACHMENT'S ATTACH_DESCs must les than 200 characters.")
    private String attachDesc1;


    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ATTACH_FILE_2", nullable = false)
    private Blob attachFile2;

    @Column(name = "ATTACH_DESC_2")
    @Size(max = 200, message = "ATTACHMENT'S ATTACH_DESCs must les than 200 characters.")
    private String attachDesc2;


    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ATTACH_FILE_3", nullable = false)
    private Blob attachFile3;

    @Column(name = "ATTACH_DESC_3")
    @Size(max = 200, message = "ATTACHMENT'S ATTACH_DESCs must les than 200 characters.")
    private String attachDesc3;


    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ATTACH_FILE_4", nullable = false)
    private Blob attachFile4;

    @Column(name = "ATTACH_DESC_4")
    @Size(max = 200, message = "ATTACHMENT'S ATTACH_DESCs must les than 200 characters.")
    private String attachDesc4;


    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ATTACH_FILE_5", nullable = false)
    private Blob attachFile5;

    @Column(name = "ATTACH_DESC_5")
    @Size(max = 200, message = "ATTACHMENT'S ATTACH_DESCs must les than 200 characters.")
    private String attachDesc5;


    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ATTACH_FILE_6", nullable = false)
    private Blob attachFile6;

    @Column(name = "ATTACH_DESC_6) ")
    @Size(max = 200, message = "ATTACHMENT'S ATTACH_DESCs must les than 200 characters.")
    private String attachDesc6;


    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ATTACH_FILE_7", nullable = false)
    private Blob attachFile7;

    @Column(name = "ATTACH_DESC_7")
    @Size(max = 200, message = "ATTACHMENT'S ATTACH_DESCs must les than 200 characters.")
    private String attachDesc7;


    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ATTACH_FILE_8", nullable = false)
    private Blob attachFile8;

    @Column(name = "ATTACH_DESC_8")
    @Size(max = 200, message = "ATTACHMENT'S ATTACH_DESCs must les than 200 characters.")
    private String attachDesc8;


    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ATTACH_FILE_9", nullable = false)
    private Blob attachFile9;

    @Column(name = "ATTACH_DESC_9")
    @Size(max = 200, message = "ATTACHMENT'S ATTACH_DESCs must les than 200 characters.")
    private String attachDesc9;


    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ATTACH_FILE_10", nullable = false)
    private Blob attachFile10;

    @Column(name = "ATTACH_DESC_10")
    @Size(max = 200, message = "ATTACHMENT'S ATTACH_DESCs must les than 200 characters.")
    private String attachDesc10;
}
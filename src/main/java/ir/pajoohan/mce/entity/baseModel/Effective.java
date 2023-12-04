package ir.pajoohan.mce.entity.baseModel;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@MappedSuperclass
public abstract class Effective extends Auditable<String> {

    @Temporal(TemporalType.DATE)
    @Column(name = "EFFECTIVE_DATE", nullable = false)
    private Date effectiveDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "DISABLE_DATE")
    private Date disableDate;

    @PrePersist
    private void onCreate() {
        if (effectiveDate == null) {
            effectiveDate = new Date();
        }
    }
}

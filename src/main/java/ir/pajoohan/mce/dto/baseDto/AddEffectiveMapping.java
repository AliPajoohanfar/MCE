package ir.pajoohan.mce.dto.baseDto;

import org.mapstruct.Mapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.METHOD})
@Retention(RUNTIME)
@Mapping(target = "effectiveDate", source = "effectiveDate")
@Mapping(target = "disableDate", source = "disableDate")
@AddAuditMapping
public @interface AddEffectiveMapping {
}
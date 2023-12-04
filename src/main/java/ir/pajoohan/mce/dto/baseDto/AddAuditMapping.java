package ir.pajoohan.mce.dto.baseDto;

import org.mapstruct.Mapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RUNTIME)
@Mapping(target = "createdBy", source = "createdBy")
@Mapping(target = "createdDate", source = "createdDate")
@Mapping(target = "lastModifiedBy", source = "lastModifiedBy")
@Mapping(target = "lastModifiedDate", source = "lastModifiedDate")
public @interface AddAuditMapping {
}
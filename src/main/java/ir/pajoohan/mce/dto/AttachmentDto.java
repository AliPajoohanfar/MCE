package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.EffectiveDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Blob;

@Getter
@Setter
@Component
public class AttachmentDto extends EffectiveDto {

    private Long id;
    private Blob attachFile1;
    private String attachDesc1;
    private Blob attachFile2;
    private String attachDesc2;
    private Blob attachFile3;
    private String attachDesc3;
    private Blob attachFile4;
    private String attachDesc4;
    private Blob attachFile5;
    private String attachDesc5;
    private Blob attachFile6;
    private String attachDesc6;
    private Blob attachFile7;
    private String attachDesc7;
    private Blob attachFile8;
    private String attachDesc8;
    private Blob attachFile9;
    private String attachDesc9;
    private Blob attachFile10;
    private String attachDesc10;
}

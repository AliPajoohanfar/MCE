package ir.pajoohan.mce.dto;

import ir.pajoohan.mce.dto.baseDto.AddEffectiveMapping;
import ir.pajoohan.mce.dto.baseDto.AddUpdateMapping;
import ir.pajoohan.mce.entity.Attachment;
import org.apache.tomcat.util.codec.binary.Base64;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface AttachmentMapper {
    AttachmentMapper INSTANCE = Mappers.getMapper(AttachmentMapper.class);

    @Named("blobToBase64String")
    static String blobToBase64String(Blob blob) throws SQLException {
        if (blob == null) {
            return null;
        }
        byte[] bytes = blob.getBytes(1l, (int) blob.length());
        String base64 = Base64.encodeBase64String(bytes);
        return base64;
    }

    @Named("base64StringToBlob")
    static Blob base64StringToBlob(String base64) throws SQLException {
        if (base64 == null) {
            return null;
        }
        byte[] bytes = Base64.decodeBase64(base64);
        return new SerialBlob(bytes);
    }

    @AddEffectiveMapping
    @Mapping(source = "attachFile1", target = "attachFile1", qualifiedByName = "blobToBase64String")
    @Mapping(source = "attachFile2", target = "attachFile2", qualifiedByName = "blobToBase64String")
    @Mapping(source = "attachFile3", target = "attachFile3", qualifiedByName = "blobToBase64String")
    @Mapping(source = "attachFile4", target = "attachFile4", qualifiedByName = "blobToBase64String")
    @Mapping(source = "attachFile5", target = "attachFile5", qualifiedByName = "blobToBase64String")
    @Mapping(source = "attachFile6", target = "attachFile6", qualifiedByName = "blobToBase64String")
    @Mapping(source = "attachFile7", target = "attachFile7", qualifiedByName = "blobToBase64String")
    @Mapping(source = "attachFile8", target = "attachFile8", qualifiedByName = "blobToBase64String")
    @Mapping(source = "attachFile9", target = "attachFile9", qualifiedByName = "blobToBase64String")
    @Mapping(source = "attachFile10", target = "attachFile10", qualifiedByName = "blobToBase64String")
    AttachmentDto attachmentToAttachmentDto(Attachment attachment);

    @Mapping(target = "effectiveDate", source = "effectiveDate")
    @Mapping(source = "attachFile1", target = "attachFile1", qualifiedByName = "base64StringToBlob")
    @Mapping(source = "attachFile2", target = "attachFile2", qualifiedByName = "base64StringToBlob")
    @Mapping(source = "attachFile3", target = "attachFile3", qualifiedByName = "base64StringToBlob")
    @Mapping(source = "attachFile4", target = "attachFile4", qualifiedByName = "base64StringToBlob")
    @Mapping(source = "attachFile5", target = "attachFile5", qualifiedByName = "base64StringToBlob")
    @Mapping(source = "attachFile6", target = "attachFile6", qualifiedByName = "base64StringToBlob")
    @Mapping(source = "attachFile7", target = "attachFile7", qualifiedByName = "base64StringToBlob")
    @Mapping(source = "attachFile8", target = "attachFile8", qualifiedByName = "base64StringToBlob")
    @Mapping(source = "attachFile9", target = "attachFile9", qualifiedByName = "base64StringToBlob")
    @Mapping(source = "attachFile10", target = "attachFile10", qualifiedByName = "base64StringToBlob")
    Attachment attachmentDtoToAttachment(AttachmentDto attachmentDto);

    @AddUpdateMapping
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(source = "attachFile1", target = "attachFile1", qualifiedByName = "base64StringToBlob")
    @Mapping(source = "attachFile2", target = "attachFile2", qualifiedByName = "base64StringToBlob")
    @Mapping(source = "attachFile3", target = "attachFile3", qualifiedByName = "base64StringToBlob")
    @Mapping(source = "attachFile4", target = "attachFile4", qualifiedByName = "base64StringToBlob")
    @Mapping(source = "attachFile5", target = "attachFile5", qualifiedByName = "base64StringToBlob")
    @Mapping(source = "attachFile6", target = "attachFile6", qualifiedByName = "base64StringToBlob")
    @Mapping(source = "attachFile7", target = "attachFile7", qualifiedByName = "base64StringToBlob")
    @Mapping(source = "attachFile8", target = "attachFile8", qualifiedByName = "base64StringToBlob")
    @Mapping(source = "attachFile9", target = "attachFile9", qualifiedByName = "base64StringToBlob")
    @Mapping(source = "attachFile10", target = "attachFile10", qualifiedByName = "base64StringToBlob")
    void updateAttachmentFromDto(AttachmentDto attachmentDto, @MappingTarget Attachment attachment);
}

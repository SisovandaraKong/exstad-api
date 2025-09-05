package istad.co.exstadbackendapi.mapper;

import istad.co.exstadbackendapi.audit.AuditableMapper;
import istad.co.exstadbackendapi.domain.Document;
import istad.co.exstadbackendapi.features.document.dto.DocumentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {MapperHelper.class, AuditableMapper.class})
public interface DocumentMapper {

    @Mapping(source = "document", target = "uri", qualifiedByName = "toPublicAccessDocument")
    @Mapping(source = "document", target = "name", qualifiedByName = "toFullDocumentName")
    @Mapping(source = ".", target = "audit")
    DocumentResponse fromDocument(Document document);
}

package istad.co.exstadbackendapi.features.scholarClass.dto;

import istad.co.exstadbackendapi.audit.AuditableDto;

public record ScholarClassResponse(
        String uuid,
        String scholarUuid,
        String scholarName,
        String classUuid,
        String className,
        Boolean isReminded,
        Boolean isPaid,
        Boolean isDeleted,
        AuditableDto audit
) {
}

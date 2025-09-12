package co.istad.exstadapi.features.scholarClass.dto;

import co.istad.exstadapi.audit.AuditableDto;

public record ScholarClassResponse(
        String uuid,
        String scholarUuid,
        String scholarName,
        String classUuid,
        String className,
        Boolean isReminded,
        Boolean isPaid,
        AuditableDto audit
) {
}

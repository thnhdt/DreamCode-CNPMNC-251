package com.cnpmnc.DreamCode.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleResponse {
    Integer id;
    String name;
    String description;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    Set<PermissionResponse> permissions;
}

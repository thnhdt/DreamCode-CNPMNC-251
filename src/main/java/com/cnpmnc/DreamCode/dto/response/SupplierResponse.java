package com.cnpmnc.DreamCode.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupplierResponse {
    Integer id;

    String name;

    String taxCode;

    String description;

    String email;

    String address;

    Boolean isActive;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;
}

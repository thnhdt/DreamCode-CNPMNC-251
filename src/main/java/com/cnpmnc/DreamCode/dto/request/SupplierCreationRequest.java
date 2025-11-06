package com.cnpmnc.DreamCode.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupplierCreationRequest {
    String name;

    String taxCode;

    String description;

    String email;

    String address;

    Boolean isActive;
}

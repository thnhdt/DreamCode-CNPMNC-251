package com.cnpmnc.DreamCode.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupplierUpdateRequest {
    String description;

    String email;

    String address;

    Boolean isActive;
}

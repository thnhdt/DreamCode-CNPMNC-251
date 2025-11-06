package com.cnpmnc.DreamCode.dto.request;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreationRequest {
    private String userName;

    private String password;

    private String avatarKey;

    private Boolean isActive;

    private Set<String> roles;
}

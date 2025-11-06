package com.cnpmnc.DreamCode.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private String userName;

    private String password;

    private String avatarKey;

    private Boolean isActive;

    private Set<RoleResponse> roles;
}

package com.cnpmnc.DreamCode.api.admin;

import com.cnpmnc.DreamCode.dto.response.RoleResponse;
import com.cnpmnc.DreamCode.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    RoleResponse toRoleResponse(Role role);
}

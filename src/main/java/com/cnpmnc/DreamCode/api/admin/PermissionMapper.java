package com.cnpmnc.DreamCode.api.admin;

import com.cnpmnc.DreamCode.dto.response.PermissionResponse;
import com.cnpmnc.DreamCode.model.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    PermissionResponse toPermissionResponse(Permission permission);
}

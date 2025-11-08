package com.cnpmnc.DreamCode.mapper;

import com.cnpmnc.DreamCode.dto.request.DepartmentCreationRequest;
import com.cnpmnc.DreamCode.dto.request.DepartmentUpdateRequest;
import com.cnpmnc.DreamCode.dto.response.DepartmentOfUserResponse;
import com.cnpmnc.DreamCode.dto.response.DepartmentResponse;
import com.cnpmnc.DreamCode.model.Department;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface DepartmentMapper {
    Department toDepartment(DepartmentCreationRequest request);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    DepartmentResponse toDepartmentResponse(Department Department);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDepartment(@MappingTarget Department Department, DepartmentUpdateRequest request);

    @IterableMapping(qualifiedByName = "toDepartmentOfUserResponse")
    List<DepartmentOfUserResponse> map(List<Department> managedDepartments);

    @Named("toDepartmentOfUserResponse")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "isActive", target = "isActive")
    DepartmentOfUserResponse toDepartmentOfUserResponse(Department department);
}

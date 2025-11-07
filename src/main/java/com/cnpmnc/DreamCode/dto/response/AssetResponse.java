package com.cnpmnc.DreamCode.dto.response;

import lombok.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class AssetResponse {
    private Integer id;
    private String name;
    private String location;
    private String description;
    private Date purchaseDate;
    private Double value;
    private String status;
    
    // Thông tin category
    private Integer categoryId;
    private String categoryName;
    
    // Thông tin supplier
    private Integer supplierId;
    private String supplierName;
    
    // Thông tin department
    private Integer departmentId;
    private String departmentName;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
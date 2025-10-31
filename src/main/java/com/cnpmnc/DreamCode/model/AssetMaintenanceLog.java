package com.cnpmnc.DreamCode.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AssetMaintenanceLog extends BaseEntity {
    private java.time.LocalDateTime maintenanceDate;
    private String performedBy;
    private String description;
    private Double cost;

    @ManyToOne
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset;
}

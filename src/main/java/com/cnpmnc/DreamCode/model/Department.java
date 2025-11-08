package com.cnpmnc.DreamCode.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Department extends BaseEntity {
    private String name;

    @Column(unique = true)
    private String code;

    @Column(name = "is_active", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User manager;

    @OneToMany(mappedBy = "department")
    private java.util.List<Asset> assets;
}

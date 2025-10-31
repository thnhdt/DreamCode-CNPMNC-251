package com.cnpmnc.DreamCode.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Department extends BaseEntity {
    private String name;

    @OneToMany(mappedBy = "department")
    private java.util.List<Asset> assets;
}

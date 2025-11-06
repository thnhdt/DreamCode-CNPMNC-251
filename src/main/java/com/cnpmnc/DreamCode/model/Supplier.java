package com.cnpmnc.DreamCode.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "suppliers")
public class Supplier extends BaseEntity {

    @Column(nullable = false, unique = true)
    String name;

    @Column(nullable = false, unique = true)
    String taxCode;

    String description;

    String email;

    String address;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    Boolean isActive = true;

    @OneToMany(mappedBy = "supplier")
    List<Asset> assets;
}
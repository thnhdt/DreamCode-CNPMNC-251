package com.cnpmnc.DreamCode.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;
import com.cnpmnc.DreamCode.model.converter.StringListConverter;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
    @Column(name = "user_name", unique = true)
    private String userName;

    private String password;

    private String avatarKey;

    @ManyToMany(mappedBy = "users")
    private List<AssetUsageLog> assetUsageLogs;

    @OneToMany(mappedBy = "approvedBy")
    private List<AssetUsageLog> approvedAssetUsageLogs;
    @ManyToMany
    private Set<Role> roles;
}
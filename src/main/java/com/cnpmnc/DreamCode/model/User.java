package com.cnpmnc.DreamCode.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class User extends BaseEntity {
    private String userName;
    private String password;
    private List<String> roles;
    private String avatarKey;

    @ManyToMany(mappedBy = "users")
    private List<AssetUsageLog> assetUsageLogs;

    @OneToMany(mappedBy = "approvedBy")
    private List<AssetUsageLog> approvedAssetUsageLogs;
}

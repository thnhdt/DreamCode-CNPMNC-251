package com.cnpmnc.DreamCode.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "users")
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

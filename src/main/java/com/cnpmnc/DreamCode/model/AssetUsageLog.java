package com.cnpmnc.DreamCode.model;

import com.cnpmnc.DreamCode.model.enumType.ApprovalStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class AssetUsageLog extends BaseEntity {
    private LocalDateTime beginTime;
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset;

    @ManyToMany
    @JoinTable(
            name = "asset_usage_log_user",
            joinColumns = {
                    @JoinColumn(name = "asset_usage_log_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id")
            }
    )
    private List<User> users;

    private String notes;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;

    private String approvalNotes;

    @ManyToOne
    @JoinColumn(name = "approved_by_id", nullable = false)
    private User approvedBy;
}

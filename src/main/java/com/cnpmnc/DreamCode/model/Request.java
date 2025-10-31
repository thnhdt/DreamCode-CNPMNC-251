package com.cnpmnc.DreamCode.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Request extends BaseEntity {
    private String title;
    private String description;
}

package org.acme.cost.domain.model;

import java.time.LocalDateTime;

import org.acme.application.domain.model.Application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cost {
    private Long id;
    private double costBuild;
    private double costRun;
    private LocalDateTime createdAt;
    private Application application;
}

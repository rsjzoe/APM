package org.acme.techBusinessValue.domain.model;

import java.time.LocalDateTime;

import org.acme.application.domain.model.Application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TechBusinessValue {
    private Long id;
    private double businessValue;
    private double technicalDebt;
    private LocalDateTime createdAt;
    private Application application;

}

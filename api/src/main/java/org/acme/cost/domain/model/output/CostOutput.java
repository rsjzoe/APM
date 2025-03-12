package org.acme.cost.domain.model.output;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostOutput {
    private Long id;
    private Long applicationId;
    private double costBuild;
    private double costRun;
    private LocalDateTime createdAt;
}

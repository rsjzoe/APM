package org.acme.techBusinessValue.domain.model.output;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TechBusinessValueOutput {
    private Long id;
    private double businessValue;
    private double technicalDebt;
    private LocalDateTime createdAt;
    private Long applicationId;

}

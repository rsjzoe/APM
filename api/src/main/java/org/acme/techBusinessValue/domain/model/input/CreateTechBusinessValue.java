package org.acme.techBusinessValue.domain.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTechBusinessValue {
    private double businessValue;
    private double technicalDebt;
    private Long appId;

}

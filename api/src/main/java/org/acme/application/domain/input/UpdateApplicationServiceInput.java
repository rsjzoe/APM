package org.acme.application.domain.input;

import java.time.LocalDateTime;
import java.util.List;

import org.acme.application.domain.model.ApplicationBase;
import org.acme.application.domain.model.Status;
import org.acme.cost.domain.model.input.CreateCostWithoutApp;
import org.acme.techBusinessValue.domain.model.input.CreateTechBusinessValueWithoutApp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class UpdateApplicationServiceInput extends ApplicationBase {
    private Long categoryId;
    private List<Long> departementIds;
    private Long classeId;
    protected double noteBusinessValue;
    protected double noteTechnicalDebt;
    private CreateCostWithoutApp costWithoutApp;
    private CreateTechBusinessValueWithoutApp techBusinessValueWithoutApp;
    private String otherDescription;

    public UpdateApplicationServiceInput(String name, String description, LocalDateTime startDate,
            LocalDateTime lastUpdate, Status status, int userTotal, double noteBusinessValue,
            double noteTechnicalDebt, Long categoryId,
            List<Long> departementIds, Long classeId, String otherDescription, CreateCostWithoutApp costWithoutApp,
            CreateTechBusinessValueWithoutApp techBusinessValueWithoutApp) {
        super(name, description, startDate, lastUpdate, status, userTotal);
        this.noteBusinessValue = noteBusinessValue;
        this.noteTechnicalDebt = noteTechnicalDebt;
        this.categoryId = categoryId;
        this.departementIds = departementIds;
        this.classeId = classeId;
        this.otherDescription = otherDescription;
        this.costWithoutApp = costWithoutApp;
        this.techBusinessValueWithoutApp = techBusinessValueWithoutApp;
    }
}

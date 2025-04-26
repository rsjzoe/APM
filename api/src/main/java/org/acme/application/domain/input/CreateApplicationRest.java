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
public class CreateApplicationRest extends ApplicationBase {
    private Long categoryId;
    private List<Long> departementIds;
    private Long classeId;
    private CreateCostWithoutApp costWithoutApp;
    private CreateTechBusinessValueWithoutApp techBusinessValueWithoutApp;

    public CreateApplicationRest(String name, String description, LocalDateTime startDate, LocalDateTime lastUpdate,
            Status status, int userTotal, Long categoryId, List<Long> departementIds, Long classeId,
            CreateCostWithoutApp costWithoutApp, CreateTechBusinessValueWithoutApp techBusinessValueWithoutApp) {
        super(name, description, startDate, lastUpdate, status, userTotal);
        this.categoryId = categoryId;
        this.departementIds = departementIds;
        this.classeId = classeId;
        this.costWithoutApp = costWithoutApp;
        this.techBusinessValueWithoutApp = techBusinessValueWithoutApp;
    }

}

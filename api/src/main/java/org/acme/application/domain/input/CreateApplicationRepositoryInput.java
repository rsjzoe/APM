package org.acme.application.domain.input;

import java.time.LocalDateTime;
import java.util.List;

import org.acme.application.domain.model.ApplicationBase;
import org.acme.application.domain.model.Status;
import org.acme.application.domain.model.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CreateApplicationRepositoryInput extends ApplicationBase {
    private Long categoryId;
    private List<Long> departementIds;
    private Long classeId;
    protected Time time;

    public CreateApplicationRepositoryInput(String name, String description, LocalDateTime startDate,
            LocalDateTime lastUpdate, Status status, Time time, int userTotal,
            Long categoryId, List<Long> departementIds, Long classeId) {
        super(name, description, startDate, lastUpdate, status, userTotal);
        this.categoryId = categoryId;
        this.departementIds = departementIds;
        this.classeId = classeId;
        this.time = time;
    }

    public CreateApplicationRepositoryInput(CreateApplicationServiceInput createApplicationServiceInput, Time time) {
        super(createApplicationServiceInput.getName(), createApplicationServiceInput.getDescription(),
                createApplicationServiceInput.getStartDate(), createApplicationServiceInput.getLastUpdate(),
                createApplicationServiceInput.getStatus(),
                createApplicationServiceInput.getUserTotal());
        this.categoryId = createApplicationServiceInput.getCategoryId();
        this.departementIds = createApplicationServiceInput.getDepartementIds();
        this.classeId = createApplicationServiceInput.getClasseId();
        this.time = time;
    }
}

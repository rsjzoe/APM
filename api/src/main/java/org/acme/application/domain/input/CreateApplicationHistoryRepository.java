package org.acme.application.domain.input;

import java.time.LocalDateTime;

import org.acme.application.domain.model.ApplicationBase;
import org.acme.application.domain.model.Status;
import org.acme.application.domain.model.Time;
import org.acme.application.domain.output.ApplicationOutput;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CreateApplicationHistoryRepository extends ApplicationBase {
    private Long appId;
    protected double noteCost;
    protected double noteTechBusiness;
    protected Long categoryId;
    protected Long departementId;
    protected Long classeId;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
    private String descriptionHistory;
    protected Long costId;
    protected Long techBusinessValueId;
    protected Time time;

    public CreateApplicationHistoryRepository(String name, String description, LocalDateTime startDate,
            LocalDateTime lastUpdate, Status status, Time time, int userTotal, Long appId, double noteCost,
            double noteTechBusiness,
            Long categoryId, Long departementId, Long classeId, LocalDateTime modifiedAt, String modifiedBy,
            String descriptionHistory,
            Long costId, Long techBusinessValueId) {
        super(name, description, startDate, lastUpdate, status, userTotal);
        this.appId = appId;
        this.noteCost = noteCost;
        this.noteTechBusiness = noteTechBusiness;
        this.categoryId = categoryId;
        this.departementId = departementId;
        this.classeId = classeId;
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
        this.descriptionHistory = descriptionHistory;
        this.costId = costId;
        this.techBusinessValueId = techBusinessValueId;
        this.time = time;
    }

    public CreateApplicationHistoryRepository(ApplicationOutput app, String modifiedBy, String descriptionHistory) {
        this(app.getName(), app.getDescription(), app.getStartDate(), app.getLastUpdate(), app.getStatus(),
                app.getTime(), app.getUserTotal(), app.getId(), app.getNoteCost(), app.getNoteTechBusiness(),
                app.getCategory().getId(),
                app.getDepartement().getId(), app.getClasse().getId(), LocalDateTime.now(), modifiedBy,
                descriptionHistory,
                app.getCurrentCost().getId(), app.getCurrentTechBusinessValue().getId());
    }
}

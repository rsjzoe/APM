package org.acme.application.domain.input;

import java.time.LocalDateTime;
import java.util.List;

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
    protected double noteBusinessValue;
    protected double noteTechnicalDebt;
    protected Long categoryId;
    protected List<Long> departementIds;
    protected Long classeId;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
    private String descriptionHistory;
    protected Long costId;
    protected Long techBusinessValueId;
    protected Time time;

    public CreateApplicationHistoryRepository(String name, String description, LocalDateTime startDate,
            LocalDateTime lastUpdate, Status status, Time time, int userTotal, Long appId, double noteBusinessValue,
            double noteTechnicalDebt,
            Long categoryId, List<Long> departementIds, Long classeId, LocalDateTime modifiedAt, String modifiedBy,
            String descriptionHistory,
            Long costId, Long techBusinessValueId) {
        super(name, description, startDate, lastUpdate, status, userTotal);
        this.appId = appId;
        this.noteBusinessValue = noteBusinessValue;
        this.noteTechnicalDebt = noteTechnicalDebt;
        this.categoryId = categoryId;
        this.departementIds = departementIds;
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
                app.getTime(), app.getUserTotal(), app.getId(), app.getNoteBusinessValue(), app.getNoteTechnicalDebt(),
                app.getCategory().getId(),
                app.getDepartements().stream().map(d -> d.getId()).toList(), app.getClasse().getId(),
                LocalDateTime.now(), modifiedBy,
                descriptionHistory,
                app.getCurrentCost() == null ? null : app.getCurrentCost().getId(),
                app.getCurrentTechBusinessValue() == null ? null : app.getCurrentTechBusinessValue().getId());
    }

    @Override
    public String toString() {
        return "CreateApplicationHistoryRepository{" +
                "appId=" + appId +
                ", noteBusinessValue=" + noteBusinessValue +
                ", noteTechnicalDebt=" + noteTechnicalDebt +
                ", categoryId=" + categoryId +
                ", departementIds=" + departementIds +
                ", classeId=" + classeId +
                ", modifiedAt=" + modifiedAt +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", descriptionHistory='" + descriptionHistory + '\'' +
                ", costId=" + costId +
                ", techBusinessValueId=" + techBusinessValueId +
                ", time=" + time +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", lastUpdate=" + lastUpdate +
                ", status=" + status +
                ", userTotal=" + userTotal +
                '}';
    }
}

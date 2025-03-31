package org.acme.application.domain.input;

import java.time.LocalDateTime;
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
public class UpdateApplicationRepositoryInput extends ApplicationBase {
    private Long categoryId;
    private Long departementId;
    private Long classeId;
    protected double noteBusinessValue;
    protected double noteTechnicalDebt;
    protected Time time;

    public UpdateApplicationRepositoryInput(String name, String description, LocalDateTime startDate,
            LocalDateTime lastUpdate, Status status, Time time, int userTotal,
            Long categoryId, Long departementId, Long classeId, double noteBusinessValue,
            double noteTechnicalDebt) {
        super(name, description, startDate, lastUpdate, status, userTotal);
        this.categoryId = categoryId;
        this.departementId = departementId;
        this.classeId = classeId;
        this.noteBusinessValue = noteBusinessValue;
        this.noteTechnicalDebt = noteTechnicalDebt;
        this.time = time;
    }

    public UpdateApplicationRepositoryInput(UpdateApplicationServiceInput updated, Time time) {
        super(updated.getName(), updated.getDescription(), updated.getStartDate(), updated.getLastUpdate(),
                updated.getStatus(), updated.getUserTotal());
        this.categoryId = updated.getCategoryId();
        this.departementId = updated.getDepartementId();
        this.classeId = updated.getClasseId();
        this.noteBusinessValue = updated.getNoteBusinessValue();
        this.noteTechnicalDebt = updated.getNoteTechnicalDebt();
        this.time = time;
    }
}

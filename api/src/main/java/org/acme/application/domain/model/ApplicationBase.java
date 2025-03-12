package org.acme.application.domain.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// objet miasa amlai projet
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationBase {
    protected String name;
    protected String description;
    protected LocalDateTime startDate;
    protected LocalDateTime lastUpdate;
    protected Status status;
    protected int userTotal;
}

package org.acme.application.domain.port.out;

import java.util.List;

import org.acme.application.domain.input.CreateApplicationHistoryInput;
import org.acme.application.domain.model.ApplicationHistory;

public interface ApplicationHistoryRepository {

    List<ApplicationHistory> listAllByApplicationId(Long applicationId);

    ApplicationHistory findById(Long id);

    ApplicationHistory create(CreateApplicationHistoryInput newApplication);

    ApplicationHistory delete(Long id);
}

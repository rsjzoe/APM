package org.acme.applicationAPM.domain.port.in;

import java.util.List;

import org.acme.applicationAPM.domain.input.CreateApplicationHistoryInput;
import org.acme.applicationAPM.domain.model.ApplicationHistory;

public interface ApplicationHistoryRest {
    List<ApplicationHistory> listAll();

    ApplicationHistory findById(Long id);

    ApplicationHistory create(CreateApplicationHistoryInput applicationHistory);

    ApplicationHistory delete(Long id);
}

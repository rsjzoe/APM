package org.acme.applicationAPM.domain.port.out;

import java.util.List;

import org.acme.applicationAPM.domain.input.CreateApplicationHistoryInput;
import org.acme.applicationAPM.domain.model.ApplicationHistory;

public interface ApplicationHistoryRepository {

    List<ApplicationHistory> listAll();

    ApplicationHistory findById(Long id);

    ApplicationHistory create(CreateApplicationHistoryInput newApplication);

    ApplicationHistory delete(Long id);
}

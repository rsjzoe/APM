package org.acme.application.domain.port.out;

import java.util.List;

import org.acme.application.domain.exception.ApplicationHistoryNotFoundException;
import org.acme.application.domain.input.CreateApplicationHistoryRepository;
import org.acme.application.domain.output.ApplicationHistoryOutput;

public interface ApplicationHistoryRepository {

    List<ApplicationHistoryOutput> listAllByApplicationId(Long applicationId);

    ApplicationHistoryOutput findById(Long id) throws ApplicationHistoryNotFoundException;

    ApplicationHistoryOutput create(CreateApplicationHistoryRepository newApplication);

}

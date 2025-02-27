package org.acme.application.domain.port.in;

import java.util.List;

import org.acme.application.domain.model.ApplicationHistory;

public interface ApplicationHistoryRest {
    List<ApplicationHistory> listAllByApplicationId(Long applicationId);

}

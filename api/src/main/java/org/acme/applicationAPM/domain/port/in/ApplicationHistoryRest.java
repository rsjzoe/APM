package org.acme.applicationAPM.domain.port.in;

import java.util.List;

import org.acme.applicationAPM.domain.model.ApplicationHistory;

public interface ApplicationHistoryRest {
    List<ApplicationHistory> listAllByApplicationId(Long applicationId);

}

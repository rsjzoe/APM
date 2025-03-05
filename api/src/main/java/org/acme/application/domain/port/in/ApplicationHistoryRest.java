package org.acme.application.domain.port.in;

import java.util.List;

import org.acme.application.domain.output.ApplicationHistoryOutput;

public interface ApplicationHistoryRest {
    List<ApplicationHistoryOutput> listAllByApplicationId(Long applicationId);

}

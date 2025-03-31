package org.acme.application.domain.port;

import org.acme.application.domain.output.ApplicationHistoryOutput;
import org.acme.application.domain.output.ApplicationOutput;

public interface GenerateDescriptionHistory {
    String generate(ApplicationOutput app, ApplicationHistoryOutput lastHistory);
}

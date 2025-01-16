package org.acme.applicationAPM.domain.port.out;

import java.util.List;

import org.acme.applicationAPM.domain.input.CreateApplicationInput;
import org.acme.applicationAPM.domain.input.UpdateApplicationInput;
import org.acme.applicationAPM.domain.model.Application;

public interface ApplicationRepository {
    List<Application> listAll();

    Application findById(Long id);

    Application create(CreateApplicationInput newApplication);

    Application update(Long id, UpdateApplicationInput updateApplication);

    Application delete(Long id);
}

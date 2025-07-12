package org.acme.techBusinessValue.domain.port.out;

import org.acme.techBusinessValue.domain.model.input.CreateTechBusinessValue;
import org.acme.techBusinessValue.domain.model.output.TechBusinessValueMonth;
import org.acme.techBusinessValue.domain.model.output.TechBusinessValueOutput;
import java.util.List;

public interface TechBusinessValueRepository {
    List<TechBusinessValueOutput> findTechBusinessValueByAppId(Long appId);

    List<TechBusinessValueMonth> findTechBusinessValueLatestPerMonthByAppId(Long appId, int year);

    TechBusinessValueOutput createTechBusinessValue(CreateTechBusinessValue techBusinessValue);

    TechBusinessValueOutput update(Long idTech, Long appId);
}

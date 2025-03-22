package org.acme.techBusinessValue.infra.database;

import org.acme.application.infra.database.ApplicationEntityHelper;
import org.acme.techBusinessValue.domain.model.input.CreateTechBusinessValue;
import org.acme.techBusinessValue.domain.model.output.TechBusinessValueMonth;
import org.acme.techBusinessValue.domain.model.output.TechBusinessValueOutput;
import org.acme.techBusinessValue.domain.port.out.TechBusinessValueRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class TechBusinessValueEntityRepository implements TechBusinessValueRepository {

    @Override
    public List<TechBusinessValueOutput> findTechBusinessValueByAppId(Long appId) {
        List<TechBusinessValueEntity> data = TechBusinessValueEntity.list("application.id", appId);
        return data.stream().map(TechBusinessValueEntity::toTechBusinessValueOutput).toList();
    }

    @Override
    public TechBusinessValueOutput createTechBusinessValue(CreateTechBusinessValue techBusinessValue) {
        TechBusinessValueEntity data = new TechBusinessValueEntity(techBusinessValue);
        data.persist();
        return data.toTechBusinessValueOutput();
    }

    @Override
    public TechBusinessValueOutput update(Long idTech, Long appId) {

        TechBusinessValueEntity data = TechBusinessValueEntity.findById(idTech);
        if (data == null) {
            return null;
        }
        data.setApplication(ApplicationEntityHelper.entityFromId(appId));
        data.persist();
        return data.toTechBusinessValueOutput();

    }

    @Override
    public List<TechBusinessValueMonth> findTechBusinessValueLatestPerMonthByAppId(Long appId) {
        PanacheQuery<TechBusinessValueEntity> data = TechBusinessValueEntity
                .find("application.id = ?1 and createdAt >= ?2", appId, LocalDateTime.now().withDayOfYear(1));

        Map<Month, TechBusinessValueOutput> latestPerMonth = data
                .stream()
                .collect(Collectors.groupingBy(
                        e -> e.getCreatedAt().getMonth(),
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparing(TechBusinessValueEntity::getCreatedAt)),
                                opt -> opt.map(TechBusinessValueEntity::toTechBusinessValueOutput).orElse(null))));

        return Arrays.stream(Month.values())
                .map(month -> new TechBusinessValueMonth(month.name(), month.getValue(),
                        latestPerMonth.getOrDefault(month, null)))
                .sorted(Comparator.comparingInt(TechBusinessValueMonth::getMonthValue))
                .toList();

    }

}

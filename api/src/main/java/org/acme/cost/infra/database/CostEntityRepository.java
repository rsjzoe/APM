package org.acme.cost.infra.database;

import org.acme.application.infra.database.ApplicationEntityHelper;
import org.acme.cost.domain.model.input.CreateCostInput;
import org.acme.cost.domain.model.output.CostOutput;
import org.acme.cost.domain.model.output.CostOutputMonth;
import org.acme.cost.domain.port.out.CostRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CostEntityRepository implements CostRepository {

    @Override
    public List<CostOutput> findCostByAppId(Long appId) {
        List<CostEntity> data = CostEntity.list("application.id", appId);
        return data.stream().map(CostEntity::toCostOutput).toList();
    }

    @Override
    public CostOutput createCost(CreateCostInput cost) {
        CostEntity data = new CostEntity(cost);
        data.persist();
        return data.toCostOutput();
    }

    @Override
    public CostOutput update(Long idCost, Long appId) {
        CostEntity data = CostEntity.findById(idCost);
        if (data == null) {
            return null;
        }
        data.setApplication(ApplicationEntityHelper.entityFromId(appId));
        data.persist();
        return data.toCostOutput();

    }

    @Override
    public List<CostOutputMonth> findCostLatestPerMonthByAppId(Long appId) {
        PanacheQuery<CostEntity> data = CostEntity
                .find("application.id = ?1 and createdAt >= ?2", appId, LocalDateTime.now().withDayOfYear(1));

        Map<Month, CostOutput> latestPerMonth = data
                .stream()
                .collect(Collectors.groupingBy(
                        e -> e.getCreatedAt().getMonth(),
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparing(CostEntity::getCreatedAt)),
                                opt -> opt.map(CostEntity::toCostOutput).orElse(null))));

        return Arrays.stream(Month.values())
                .map(month -> new CostOutputMonth(month.name(), month.getValue(),
                        latestPerMonth.getOrDefault(month, null)))
                .sorted(Comparator.comparingInt(CostOutputMonth::getMonthValue))
                .toList();
    }

}

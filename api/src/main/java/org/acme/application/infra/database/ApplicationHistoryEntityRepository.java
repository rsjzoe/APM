package org.acme.application.infra.database;

import java.util.List;

import org.acme.application.domain.exception.ApplicationHistoryNotFoundException;
import org.acme.application.domain.input.CreateApplicationHistoryRepository;
import org.acme.application.domain.output.ApplicationHistoryOutput;
import org.acme.application.domain.port.out.ApplicationHistoryRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ApplicationHistoryEntityRepository implements ApplicationHistoryRepository {

    @Override
    public List<ApplicationHistoryOutput> listAllByApplicationId(Long applicationId) {
        List<ApplicationHistoryEntity> data = ApplicationHistoryEntity.list("appId = ?1 order by modifiedAt desc",
                applicationId);
        return data.stream()
                .map(ApplicationHistoryEntity::toOutput)
                .toList();
    }

    @Override
    public ApplicationHistoryOutput findById(Long id) throws ApplicationHistoryNotFoundException {
        ApplicationHistoryEntity entity = ApplicationHistoryEntity.findById(id);
        if (entity == null) {
            throw new ApplicationHistoryNotFoundException();
        }
        return entity.toOutput();
    }

    @Override
    @Transactional
    public ApplicationHistoryOutput create(CreateApplicationHistoryRepository newApplication) {
        ApplicationHistoryEntity entity = new ApplicationHistoryEntity(newApplication);
        ApplicationHistoryEntity.persist(entity);
        return entity.toOutput();
    }

}

package org.acme.application.infra.database;

import java.util.List;

import org.acme.application.domain.model.input.CreateApplicationHistoryRepository;
import org.acme.application.domain.model.output.ApplicationHistoryOutput;
import org.acme.application.domain.port.out.ApplicationHistoryRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ApplicationHistoryEntityRepository implements ApplicationHistoryRepository {

    @Override
    public List<ApplicationHistoryOutput> listAllByApplicationId(Long applicationId) {
        List<ApplicationHistoryEntity> data = ApplicationHistoryEntity.list("applicationEntity.id", applicationId);
        return data.stream()
                .map(ApplicationHistoryEntity::toOutput)
                .toList();
    }

    @Override
    public ApplicationHistoryOutput findById(Long id) {
        ApplicationHistoryEntity entity = ApplicationHistoryEntity.findById(id);
        if (entity == null) {
            return null;
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

    @Override
    @Transactional
    public ApplicationHistoryOutput delete(Long id) {
        ApplicationHistoryEntity entity = ApplicationHistoryEntity.findById(id);
        if (entity != null) {
            entity.delete();
            return entity.toOutput();
        }
        return null;
    }
}

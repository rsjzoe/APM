package org.acme.applicationAPM.infra.database;

import java.util.List;

import org.acme.applicationAPM.domain.input.CreateApplicationHistoryInput;
import org.acme.applicationAPM.domain.model.ApplicationHistory;
import org.acme.applicationAPM.domain.port.out.ApplicationHistoryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ApplicationHistoryEntityRepository implements ApplicationHistoryRepository {

    @Override
    public List<ApplicationHistory> listAllByApplicationId(Long applicationId) {
        List<ApplicationHistoryEntity> data = ApplicationHistoryEntity.list("applicationEntity.id", applicationId);
        return data.stream()
                .map(ApplicationHistoryEntity::toApplicationHistory)
                .toList();
    }

    @Override
    public ApplicationHistory findById(Long id) {
        ApplicationHistoryEntity entity = ApplicationHistoryEntity.findById(id);
        if (entity == null) {
            return null;
        }
        return entity.toApplicationHistory();
    }

    @Override
    @Transactional
    public ApplicationHistory create(CreateApplicationHistoryInput newApplication) {
        ApplicationHistoryEntity entity = new ApplicationHistoryEntity(newApplication);
        ApplicationHistoryEntity.persist(entity);
        return entity.toApplicationHistory();
    }

    @Override
    @Transactional
    public ApplicationHistory delete(Long id) {
        ApplicationHistoryEntity entity = ApplicationHistoryEntity.findById(id);
        if (entity != null) {
            entity.delete();
            return entity.toApplicationHistory();
        }
        return null;
    }
}

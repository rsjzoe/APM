package org.acme.application.infra.database;

import java.util.List;
import java.util.stream.Collectors;

import org.acme.application.domain.exception.ApplicationNotFoundException;
import org.acme.application.domain.input.CreateApplicationRepositoryInput;
import org.acme.application.domain.input.UpdateApplicationRepositoryInput;
import org.acme.application.domain.output.ApplicationOutput;
import org.acme.application.domain.output.PaginationOutput;
import org.acme.application.domain.port.out.ApplicationRepository;
import org.acme.application.domain.query.ApplicationQuery;
import org.acme.departement.infra.database.DepartementEntity;

public class ApplicationEntityRepository implements ApplicationRepository {

    @Override
    public PaginationOutput<ApplicationOutput> listAll(ApplicationQuery query) {
        StringBuilder jpql = new StringBuilder("isDeleted = ?1");
        java.util.List<Object> params = new java.util.ArrayList<>();
        params.add(false);

        int paramIndex = 2;
        if (query != null) {
            if (query.getYear() != null) {
                jpql.append(" and EXTRACT(YEAR FROM startDate) = ?" + paramIndex);
                params.add(query.getYear());
                paramIndex++;
            }
            if (query.getSearch() != null && !query.getSearch().isBlank()) {
                jpql.append(" and (LOWER(name) LIKE ?" + paramIndex + " OR LOWER(description) LIKE ?" + (paramIndex + 1)
                        + ")");
                String like = "%" + query.getSearch().toLowerCase() + "%";
                params.add(like);
                params.add(like);
                paramIndex += 2;
            }
            if (query.getDepartementId() != null) {
                jpql.append(" and ?" + paramIndex + " MEMBER OF departements");
                DepartementEntity depEntity = DepartementEntity.findById(query.getDepartementId());
                params.add(depEntity);
                paramIndex++;
            }
        }

        int page = query != null && query.getPage() != null ? query.getPage() : 1;
        int size = query != null && query.getSize() != null ? query.getSize() : 9999;
        long total = ApplicationEntity.count(jpql.toString(), params.toArray());
        List<ApplicationEntity> data = ApplicationEntity.find(jpql.toString(), params.toArray())
                .page(page - 1, size)
                .list();
        List<ApplicationOutput> items = data.stream()
                .map(entity -> entity.toApplicationOutput())
                .collect(Collectors.toList());
        int totalPages = (int) Math.ceil((double) total / size);
        return new PaginationOutput<>(items, total, totalPages, page, size);
    }

    @Override
    public ApplicationOutput findById(Long id) throws ApplicationNotFoundException {
        ApplicationEntity data = ApplicationEntity.findById(id);
        if (data == null) {
            throw new ApplicationNotFoundException();
        }
        return data.toApplicationOutput();
    }

    @Override
    public ApplicationOutput create(CreateApplicationRepositoryInput newApplication) {
        ApplicationEntity data = new ApplicationEntity(newApplication);
        data.persist();
        return data.toApplicationOutput();
    }

    @Override
    public ApplicationOutput update(Long id, UpdateApplicationRepositoryInput updateApplication)
            throws ApplicationNotFoundException {
        ApplicationEntity data = ApplicationEntity.findById(id);
        if (data == null) {
            throw new ApplicationNotFoundException();
        }
        data.updateData(updateApplication);
        data.persist();
        return data.toApplicationOutput();
    }

    @Override
    public ApplicationOutput delete(Long id) throws ApplicationNotFoundException {
        ApplicationEntity data = ApplicationEntity.findById(id);
        if (data == null) {
            throw new ApplicationNotFoundException();
        }
        data.setDeleted(true);
        data.persist();
        return data.toApplicationOutput();
    }

    @Override
    public List<ApplicationOutput> deletedApplication() {
        List<ApplicationEntity> data = ApplicationEntity.list("isDeleted", true);
        return data.stream()
                .map(entity -> (entity).toApplicationOutput())
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationOutput restore(Long id) throws ApplicationNotFoundException {
        ApplicationEntity data = ApplicationEntity.findById(id);
        if (data == null) {
            throw new ApplicationNotFoundException();
        }
        data.setDeleted(false);
        data.persist();
        return data.toApplicationOutput();
    }
}

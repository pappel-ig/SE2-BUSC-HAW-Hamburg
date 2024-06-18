package de.haw_hamburg.mensamatch.adapter.persistence.criteria;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import de.haw_hamburg.mensamatch.adapter.persistence.criteria.model.CriteriaDao;
import de.haw_hamburg.mensamatch.domain.criteria.CriteriaRepository;
import de.haw_hamburg.mensamatch.domain.criteria.model.CriteriaSelection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

@Repository
@RequiredArgsConstructor
public class MongoCriteriaRepository implements CriteriaRepository {

    private final MongoCollection<CriteriaDao> collection;

    @Override
    public CriteriaSelection getCriteriaForUser(UUID userId) {
        final CriteriaDao criteriaDao = ensureCriteriaCollectionExists(userId);
        return criteriaDao.toCriteriaSelection();
    }

    @Override
    public boolean updateCriteria(CriteriaSelection criteriaSelection) {
        ensureCriteriaCollectionExists(criteriaSelection.getId());
        collection.findOneAndUpdate(
                eq("_id", criteriaSelection.getId().toString()),
                Updates.combine(
                        set("include", criteriaSelection.getInclude()),
                        set("exclude", criteriaSelection.getExclude())));
        return true;
    }

    private CriteriaDao ensureCriteriaCollectionExists(UUID id) {
        final Optional<CriteriaDao> criteria = Optional.ofNullable(collection.find(eq("_id", id.toString())).first());
        if (criteria.isEmpty()) {
            final CriteriaDao emptyCriteria = CriteriaDao.builder()
                    .id(id.toString())
                    .include(new HashSet<>())
                    .exclude(new HashSet<>())
                    .build();
            collection.insertOne(emptyCriteria);
            return emptyCriteria;
        }
        return criteria.get();
    }
}

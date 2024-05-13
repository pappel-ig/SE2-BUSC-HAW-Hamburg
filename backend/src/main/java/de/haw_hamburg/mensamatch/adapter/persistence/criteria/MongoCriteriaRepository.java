package de.haw_hamburg.mensamatch.adapter.persistence.criteria;

import com.mongodb.client.MongoCollection;
import de.haw_hamburg.mensamatch.adapter.persistence.criteria.model.CriteriaDao;
import de.haw_hamburg.mensamatch.domain.criteria.CriteriaRepository;
import de.haw_hamburg.mensamatch.domain.criteria.model.CriteriaSelection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

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
        ensureCriteriaCollectionExists(criteriaSelection.getUserId());
        collection.findOneAndUpdate(
                eq("userId", criteriaSelection.getUserId().toString()),
                set("criteria", criteriaSelection.getCriteria()));
        return true;
    }

    private CriteriaDao ensureCriteriaCollectionExists(UUID userId) {
        final Optional<CriteriaDao> criteria = Optional.ofNullable(collection.find(eq("userId", userId.toString())).first());
        if (criteria.isEmpty()) {
            final CriteriaDao emptyCriteria = CriteriaDao.builder()
                    .userId(userId.toString())
                    .criteria(new HashSet<>())
                    .build();
            collection.insertOne(emptyCriteria);
            return emptyCriteria;
        }
        return criteria.get();
    }
}

package de.haw_hamburg.mensamatch.domain.criteria;

import de.haw_hamburg.mensamatch.domain.criteria.model.CriteriaSelection;
import de.haw_hamburg.mensamatch.domain.user.model.User;

import java.util.Optional;
import java.util.UUID;

public interface CriteriaRepository {

    CriteriaSelection getCriteriaForUser(UUID userId);
    boolean updateCriteria(CriteriaSelection criteriaSelection);

}

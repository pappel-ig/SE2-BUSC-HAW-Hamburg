package de.haw_hamburg.mensamatch.domain.criteria.model;

import de.haw_hamburg.mensamatch.domain.user.model.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;
import java.util.UUID;

@Getter
@Builder(toBuilder = true)
public class CriteriaSelection {

    private final UUID userId;
    private final Set<String> criteria;

}

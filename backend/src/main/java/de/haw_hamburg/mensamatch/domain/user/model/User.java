package de.haw_hamburg.mensamatch.domain.user.model;

import de.haw_hamburg.mensamatch.domain.criteria.model.CriteriaSelection;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder(toBuilder = true)
public class User {

    private final UUID id;
    private final String username;
    private final String password;
    private final List<String> roles;
    private final CriteriaSelection criteriaSelection;

}

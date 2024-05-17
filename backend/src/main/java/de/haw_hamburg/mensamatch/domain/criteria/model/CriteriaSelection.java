package de.haw_hamburg.mensamatch.domain.criteria.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;
import java.util.UUID;

@Getter
@Builder(toBuilder = true)
public class CriteriaSelection {

    private final UUID id;
    private final Set<Criterum> criteria;

}

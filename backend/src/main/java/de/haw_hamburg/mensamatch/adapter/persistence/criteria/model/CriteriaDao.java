package de.haw_hamburg.mensamatch.adapter.persistence.criteria.model;

import de.haw_hamburg.mensamatch.domain.criteria.model.CriteriaSelection;
import de.haw_hamburg.mensamatch.domain.criteria.model.Criterum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Document
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CriteriaDao {

    @Id
    private String id;
    private Set<String> include;
    private Set<String> exclude;

    public CriteriaSelection toCriteriaSelection() {
        return CriteriaSelection.builder()
                .id(UUID.fromString(id))
                .include(include.stream().map(Criterum::valueOf).collect(Collectors.toSet()))
                .exclude(exclude.stream().map(Criterum::valueOf).collect(Collectors.toSet()))
                .build();
    }
}

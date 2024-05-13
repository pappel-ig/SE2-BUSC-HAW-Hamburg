package de.haw_hamburg.mensamatch.adapter.persistence.criteria.model;

import de.haw_hamburg.mensamatch.domain.criteria.model.CriteriaSelection;
import de.haw_hamburg.mensamatch.domain.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.Set;
import java.util.UUID;

@Data
@Document
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CriteriaDao {

    @Id
    private String userId;
    private Set<String> criteria;

    public CriteriaSelection toCriteriaSelection() {
        return CriteriaSelection.builder()
                .userId(UUID.fromString(userId))
                .criteria(criteria)
                .build();
    }
}

package de.haw_hamburg.mensamatch.adapter.api.criteria.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
@Getter
@Builder(toBuilder = true)
@JsonInclude()
public class CriteriaSelectionDto {

    private final Map<String, String> include;
    private final Map<String, String> exclude;

}

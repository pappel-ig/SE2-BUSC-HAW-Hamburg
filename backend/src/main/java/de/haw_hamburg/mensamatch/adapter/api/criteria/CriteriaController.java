package de.haw_hamburg.mensamatch.adapter.api.criteria;

import de.haw_hamburg.mensamatch.adapter.api.criteria.model.CriteriaSelectionDto;
import de.haw_hamburg.mensamatch.domain.criteria.service.CriteriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/criteria")
@RequiredArgsConstructor
public class CriteriaController {

    private final CriteriaService criteriaService;

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public Map<String, String> getAllCriteria() {
        return criteriaService.availableCriterias().stream().collect(Collectors.toMap(Enum::name, criterum -> criterum.text));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public CriteriaSelectionDto getIncludeCriteria(Authentication authentication) {
        return CriteriaSelectionDto.builder()
                .include(criteriaService.getIncludeCriteria(authentication.getName()).stream().collect(Collectors.toMap(Enum::name, criterum -> criterum.text)))
                .exclude(criteriaService.getExcludeCriteria(authentication.getName()).stream().collect(Collectors.toMap(Enum::name, criterum -> criterum.text)))
                .build();
    }

    @RequestMapping(value = "include/{criteriaToRemove}", method = RequestMethod.DELETE)
    public boolean removeIncludeCriteria(Authentication authentication, @PathVariable("criteriaToRemove") String criteriaToRemove) {
        return criteriaService.removeIncludeCriteria(authentication.getName(), Set.of(criteriaToRemove));
    }

    @RequestMapping(value = "exclude/{criteriaToRemove}", method = RequestMethod.DELETE)
    public boolean removeExcludeCriteria(Authentication authentication, @PathVariable("criteriaToRemove") String criteriaToRemove) {
        return criteriaService.removeExcludeCriteria(authentication.getName(), Set.of(criteriaToRemove));
    }

    @RequestMapping(value = "include/{criteriaToAdd}", method = RequestMethod.POST)
    public boolean addIncludeCriteria(Authentication authentication, @PathVariable("criteriaToAdd") String criteriaToAdd) {
        return criteriaService.addNewIncludeCriteria(authentication.getName(), Set.of(criteriaToAdd));
    }

    @RequestMapping(value = "exclude/{criteriaToAdd}", method = RequestMethod.POST)
    public boolean addExcludeCriteria(Authentication authentication, @PathVariable("criteriaToAdd") String criteriaToAdd) {
        return criteriaService.addNewExcludeCriteria(authentication.getName(), Set.of(criteriaToAdd));
    }
}

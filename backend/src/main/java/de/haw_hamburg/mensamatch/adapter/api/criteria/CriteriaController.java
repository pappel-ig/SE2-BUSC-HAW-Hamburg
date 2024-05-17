package de.haw_hamburg.mensamatch.adapter.api.criteria;

import de.haw_hamburg.mensamatch.domain.criteria.model.Criterum;
import de.haw_hamburg.mensamatch.domain.criteria.service.CriteriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/criteria")
@RequiredArgsConstructor
public class CriteriaController {

    private final CriteriaService criteriaService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Map<String, String> getAllCriteria() {
        return criteriaService.getAlLCriterias().stream().collect(Collectors.toMap(Enum::name, criterum -> criterum.text));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Set<Criterum> getCriteriaForUser(Authentication authentication) {
        return criteriaService.getCriteriaForUser(authentication.getName());
    }

    @RequestMapping(method = RequestMethod.POST)
    public boolean addCriteriaForUser(Authentication authentication, @RequestBody Set<String> criteriasToAdd) {
        return criteriaService.addNewCriteria(authentication.getName(), criteriasToAdd);
    }
}

package de.haw_hamburg.mensamatch.adapter.api.criteria;

import de.haw_hamburg.mensamatch.domain.criteria.service.CriteriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/criteria")
@RequiredArgsConstructor
public class CriteriaController {

    private final CriteriaService criteriaService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Set<String> getCriteriaForUser(Authentication authentication) {
        return criteriaService.getCriteriaForUser(authentication.getName());
    }

    @RequestMapping(method = RequestMethod.POST)
    public boolean addCriteriaForUser(Authentication authentication, @RequestBody Set<String> criteriasToAdd) {
        return criteriaService.addNewCriteria(authentication.getName(), criteriasToAdd);
    }
}

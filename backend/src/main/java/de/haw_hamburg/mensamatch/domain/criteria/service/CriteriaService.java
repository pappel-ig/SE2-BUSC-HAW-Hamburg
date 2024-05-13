package de.haw_hamburg.mensamatch.domain.criteria.service;

import de.haw_hamburg.mensamatch.domain.criteria.CriteriaRepository;
import de.haw_hamburg.mensamatch.domain.criteria.model.CriteriaSelection;
import de.haw_hamburg.mensamatch.domain.user.UserRepository;
import de.haw_hamburg.mensamatch.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CriteriaService {

    private final UserRepository userRepository;
    private final CriteriaRepository criteriaRepository;

    public boolean addNewCriteria(String username, Set<String> criteria) {
        final Optional<User> optionalUser = userRepository.findUser(username);
        if (optionalUser.isPresent()) {
            final User user = optionalUser.get();
            final CriteriaSelection criteriaForUser = criteriaRepository.getCriteriaForUser(user.getId());
            final boolean added = criteriaForUser.getCriteria().addAll(criteria);
            return added && criteriaRepository.updateCriteria(criteriaForUser);
        }
        return false;
    }

    public Set<String> getCriteriaForUser(String username) {
        final Optional<User> optionalUser = userRepository.findUser(username);
        if (optionalUser.isPresent()) {
            final User user = optionalUser.get();
            return criteriaRepository.getCriteriaForUser(user.getId()).getCriteria();
        }
        return new HashSet<>();
    }
}

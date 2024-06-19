package de.haw_hamburg.mensamatch.domain.recommend.service;

import de.haw_hamburg.mensamatch.domain.criteria.CriteriaRepository;
import de.haw_hamburg.mensamatch.domain.criteria.model.CriteriaSelection;
import de.haw_hamburg.mensamatch.domain.criteria.model.Criterum;
import de.haw_hamburg.mensamatch.domain.meal.MealRepository;
import de.haw_hamburg.mensamatch.domain.meal.model.Meal;
import de.haw_hamburg.mensamatch.domain.user.UserRepository;
import de.haw_hamburg.mensamatch.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final UserRepository userRepository;
    private final CriteriaRepository criteriaRepository;
    private final MealRepository mealRepository;

    public List<Meal> recommend(String username) {
        final Optional<User> user = userRepository.findUser(username);
        if (user.isEmpty()) return new ArrayList<>();

        final CriteriaSelection criteriaForUser = criteriaRepository.getCriteriaForUser(user.get().getId());
        final List<Meal> meals = mealRepository.getFrom(LocalDate.now());

        final Set<String> include = criteriaForUser.getInclude().stream().map(Criterum::getText).collect(Collectors.toSet());
        final Set<String> exclude = criteriaForUser.getExclude().stream().map(Criterum::getText).collect(Collectors.toSet());

        return meals.stream()
                .filter(meal ->
                        include.isEmpty() || meal.getCriteria().stream().anyMatch(include::contains))
                .filter(meal ->
                        exclude.isEmpty() || meal.getCriteria().stream().noneMatch(exclude::contains))
                .collect(Collectors.toList());
    }
}

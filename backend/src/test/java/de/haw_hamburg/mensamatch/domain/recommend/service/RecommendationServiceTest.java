package de.haw_hamburg.mensamatch.domain.recommend.service;

import de.haw_hamburg.mensamatch.domain.criteria.CriteriaRepository;
import de.haw_hamburg.mensamatch.domain.criteria.model.CriteriaSelection;
import de.haw_hamburg.mensamatch.domain.criteria.model.Criterum;
import de.haw_hamburg.mensamatch.domain.meal.MealRepository;
import de.haw_hamburg.mensamatch.domain.meal.model.Meal;
import de.haw_hamburg.mensamatch.domain.user.UserRepository;
import de.haw_hamburg.mensamatch.domain.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

class RecommendationServiceTest {

    UUID userId = UUID.fromString("a3590927-c653-4881-bb9d-20c97e7d8d60");

    UserRepository userRepository;
    CriteriaRepository criteriaRepository;
    MealRepository mealRepository;

    RecommendationService recommendationService;

    @BeforeEach
    void setup() {
        userRepository = Mockito.mock(UserRepository.class);
        criteriaRepository = Mockito.mock(CriteriaRepository.class);
        mealRepository = Mockito.mock(MealRepository.class);
        recommendationService = new RecommendationService(userRepository, criteriaRepository, mealRepository);
    }

    @Test
    void shouldReturnRecommendations() {
        final List<Meal> mealList = List.of(
                Meal.builder().name("meal1").build(),
                Meal.builder().name("meal2").build(),
                Meal.builder().name("meal3").build()
        );

        Mockito.when(userRepository.findUser(any())).thenReturn(Optional.of(User.builder().id(userId).build()));
        Mockito.when(criteriaRepository.getCriteriaForUser(any())).thenReturn(CriteriaSelection.builder().include(Set.of()).exclude(Set.of()).build());
        Mockito.when(mealRepository.getFrom(any())).thenReturn(mealList);

        final List<Meal> actual = recommendationService.recommend("testUser");

        assertThat(actual.size()).isEqualTo(mealList.size());
        assertThat(actual).isEqualTo(mealList);
    }

    @Test
    void shouldReturnEmptyIfNoMealMatchesFilter() {
        final List<Meal> mealList = List.of(
                Meal.builder().name("meal1").criteria(List.of("Vegan")).build(),
                Meal.builder().name("meal2").criteria(List.of("Vegetarisch")).build(),
                Meal.builder().name("meal3").criteria(List.of("Ei und Eierzeugnisse")).build()
        );

        Mockito.when(userRepository.findUser(any())).thenReturn(Optional.of(User.builder().id(userId).build()));
        Mockito.when(criteriaRepository.getCriteriaForUser(any())).thenReturn(CriteriaSelection.builder().include(Set.of()).exclude(Set.of(Criterum.VEGAN, Criterum.EI, Criterum.VEGETARISCH)).build());
        Mockito.when(mealRepository.getFrom(any())).thenReturn(mealList);

        final List<Meal> actual = recommendationService.recommend("testUser");

        assertThat(actual).isEmpty();
    }

    @Test
    void shouldReturnFirstMealIfChosenEverything() {
        final List<Meal> mealList = List.of(
                Meal.builder().name("meal1").criteria(List.of("Vegan")).build(),
                Meal.builder().name("meal2").criteria(List.of("Vegetarisch")).build(),
                Meal.builder().name("meal3").criteria(List.of("Ei und Eierzeugnisse")).build()
        );

        Mockito.when(userRepository.findUser(any())).thenReturn(Optional.of(User.builder().id(userId).build()));
        Mockito.when(criteriaRepository.getCriteriaForUser(any())).thenReturn(CriteriaSelection.builder().include(Set.of(Criterum.VEGAN, Criterum.EI, Criterum.VEGETARISCH)).exclude(Set.of()).build());
        Mockito.when(mealRepository.getFrom(any())).thenReturn(mealList);

        final List<Meal> actual = recommendationService.recommend("testUser");

        assertThat(actual).isEqualTo(mealList);
    }

    @Test
    void shouldReturnNothingIfTooManyNegatives() {
        final List<Meal> mealList = new ArrayList<>(List.of(
                Meal.builder().name("meal1").criteria(List.of("Vegan","Geschwärzt")).build(),
                Meal.builder().name("meal2").criteria(List.of("Vegetarisch, Ei und Eierzeugnisse")).build(),
                Meal.builder().name("meal3").criteria(List.of("Geschwärzt")).build(),
                Meal.builder().name("meal4").criteria(List.of("Ei und Eierzeugnisse")).build()
        ));

        Mockito.when(userRepository.findUser(any())).thenReturn(Optional.of(User.builder().id(userId).build()));
        Mockito.when(criteriaRepository.getCriteriaForUser(any())).thenReturn(CriteriaSelection.builder().include(Set.of(Criterum.VEGAN, Criterum.VEGETARISCH)).exclude(Set.of(Criterum.EI, Criterum.GESCHWAERZT)).build());
        Mockito.when(mealRepository.getFrom(any())).thenReturn(mealList);

        final List<Meal> actual = recommendationService.recommend("testUser");

        assertThat(actual).isEmpty();
    }

    @Test
    void shouldReturnOneMealIfManyNegatives() {
        final List<Meal> mealList = new ArrayList<>(List.of(
                Meal.builder().name("meal1").criteria(List.of("Vegan","Geschwärzt")).build(),
                Meal.builder().name("meal2").criteria(List.of("Vegetarisch")).build(),
                Meal.builder().name("meal3").criteria(List.of("Geschwärzt")).build(),
                Meal.builder().name("meal4").criteria(List.of("Ei und Eierzeugnisse")).build()
        ));

        Mockito.when(userRepository.findUser(any())).thenReturn(Optional.of(User.builder().id(userId).build()));
        Mockito.when(criteriaRepository.getCriteriaForUser(any())).thenReturn(CriteriaSelection.builder().include(Set.of()).exclude(Set.of(Criterum.EI, Criterum.GESCHWAERZT)).build());
        Mockito.when(mealRepository.getFrom(any())).thenReturn(mealList);

        final List<Meal> actual = recommendationService.recommend("testUser");

        mealList.remove(0);
        mealList.remove(1);
        mealList.remove(1);

        assertThat(actual).isEqualTo(mealList);
    }

    @Test
    void shouldReturnSomeMealsIfSomeCriteria() {
        final List<Meal> mealList = new ArrayList<>(List.of(
                Meal.builder().name("meal1").criteria(List.of("Vegan","Geschwärzt")).build(),
                Meal.builder().name("meal2").criteria(List.of("Vegetarisch")).build(),
                Meal.builder().name("meal3").criteria(List.of("Geschwärzt")).build(),
                Meal.builder().name("meal4").criteria(List.of("Ei und Eierzeugnisse")).build()
        ));

        Mockito.when(userRepository.findUser(any())).thenReturn(Optional.of(User.builder().id(userId).build()));
        Mockito.when(criteriaRepository.getCriteriaForUser(any())).thenReturn(CriteriaSelection.builder().include(Set.of(Criterum.GESCHWAERZT, Criterum.VEGETARISCH)).exclude(Set.of(Criterum.EI, Criterum.VEGAN)).build());
        Mockito.when(mealRepository.getFrom(any())).thenReturn(mealList);

        final List<Meal> actual = recommendationService.recommend("testUser");

        mealList.remove(0);
        mealList.remove(2);

        assertThat(actual).isEqualTo(mealList);
    }
}
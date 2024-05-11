package de.haw_hamburg.mensamatch.domain.meal.service;

import de.haw_hamburg.mensamatch.domain.meal.MealRepository;
import de.haw_hamburg.mensamatch.domain.meal.model.Meal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MealService {

    private final MealRepository mealRepository;

    public List<Meal> getTodayMeals() {
        return mealRepository.getFrom(LocalDate.now());
    }

}

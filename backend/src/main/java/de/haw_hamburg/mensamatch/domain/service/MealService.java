package de.haw_hamburg.mensamatch.domain.service;

import de.haw_hamburg.mensamatch.domain.MealRepository;
import de.haw_hamburg.mensamatch.domain.model.Meal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

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

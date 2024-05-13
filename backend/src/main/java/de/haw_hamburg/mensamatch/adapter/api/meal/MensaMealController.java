package de.haw_hamburg.mensamatch.adapter.api.meal;

import de.haw_hamburg.mensamatch.adapter.api.meal.model.MensaMealDto;
import de.haw_hamburg.mensamatch.domain.meal.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mensa-meal")
@RequiredArgsConstructor
public class MensaMealController {

    private final MealService mealService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<MensaMealDto> getTodayMeal() {
        return mealService.getTodayMeals().stream().map(MensaMealDto::from).toList();
    }
}
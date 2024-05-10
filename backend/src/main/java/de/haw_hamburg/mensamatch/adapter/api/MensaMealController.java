package de.haw_hamburg.mensamatch.adapter.api;

import de.haw_hamburg.mensamatch.domain.model.Meal;
import de.haw_hamburg.mensamatch.domain.service.MealService;
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

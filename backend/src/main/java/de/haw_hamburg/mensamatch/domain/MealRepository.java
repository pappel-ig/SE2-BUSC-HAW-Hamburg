package de.haw_hamburg.mensamatch.domain;

import de.haw_hamburg.mensamatch.domain.model.Meal;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository {

    void store(Meal meal);
    List<Meal> getFrom(LocalDate date);

}

package de.haw_hamburg.mensamatch.domain.meal;

import de.haw_hamburg.mensamatch.domain.meal.model.Meal;

import java.util.List;

public interface MensaRepository {

    List<Meal> getTodaysMeal();

}

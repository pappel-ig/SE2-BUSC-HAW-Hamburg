package de.haw_hamburg.mensamatch.domain;

import de.haw_hamburg.mensamatch.domain.model.Meal;

import java.util.List;

public interface MensaRepository {

    List<Meal> getTodaysMeal();

}

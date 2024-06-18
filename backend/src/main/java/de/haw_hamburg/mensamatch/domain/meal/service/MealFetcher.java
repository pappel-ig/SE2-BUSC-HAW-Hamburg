package de.haw_hamburg.mensamatch.domain.meal.service;

import de.haw_hamburg.mensamatch.domain.meal.MealRepository;
import de.haw_hamburg.mensamatch.domain.meal.MensaRepository;
import de.haw_hamburg.mensamatch.domain.meal.model.Meal;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class MealFetcher {

    private static final Logger log = LoggerFactory.getLogger(MealFetcher.class);
    private final MealRepository mealRepository;
    private final MensaRepository mensaRepository;

    @PostConstruct
    public void triggerInitial() {
        fetchTodaysMeal();
    }

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
    public void fetchTodaysMeal() {
        log.info("Fetching Meals");
        for (Meal meal : mensaRepository.getTodaysMeal()) {
            mealRepository.store(meal);
        }
    }
}

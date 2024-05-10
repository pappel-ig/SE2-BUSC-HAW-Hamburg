package de.haw_hamburg.mensamatch.domain.service;

import de.haw_hamburg.mensamatch.domain.MealRepository;
import de.haw_hamburg.mensamatch.domain.MensaRepository;
import de.haw_hamburg.mensamatch.domain.model.Meal;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class MealFetcher {

    private final MealRepository mealRepository;
    private final MensaRepository mensaRepository;

    @PostConstruct
    public void triggerInitial() {
        fetchTodaysMeal();
    }

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.HOURS)
    public void fetchTodaysMeal() {
        for (Meal meal : mensaRepository.getTodaysMeal()) {
            mealRepository.store(meal);
        }
    }
}

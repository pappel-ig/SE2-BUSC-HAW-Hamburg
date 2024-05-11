package de.haw_hamburg.mensamatch.adapter.rest.meal;


import de.haw_hamburg.mensamatch.adapter.rest.meal.model.OpenMensaDto;
import de.haw_hamburg.mensamatch.domain.meal.MensaRepository;
import de.haw_hamburg.mensamatch.domain.meal.model.Meal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Repository
public class RestMensaRepository implements MensaRepository {

    private final RestClient openMensaRestClient;

    @Override
    public List<Meal> getTodaysMeal() {
        return Objects.requireNonNull(openMensaRestClient
                        .get()
                        .uri("/mensahd/today/hamburg_berlinertor.xml")
                        .retrieve()
                        .body(OpenMensaDto.class))
                .toMeals();
    }
}

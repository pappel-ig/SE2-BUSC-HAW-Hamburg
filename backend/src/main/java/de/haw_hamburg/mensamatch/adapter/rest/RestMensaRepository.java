package de.haw_hamburg.mensamatch.adapter.rest;


import de.haw_hamburg.mensamatch.adapter.rest.model.OpenMensaDto;
import de.haw_hamburg.mensamatch.domain.MensaRepository;
import de.haw_hamburg.mensamatch.domain.model.Meal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;
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

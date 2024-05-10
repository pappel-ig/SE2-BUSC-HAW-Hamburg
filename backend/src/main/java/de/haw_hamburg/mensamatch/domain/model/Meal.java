package de.haw_hamburg.mensamatch.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.money.MonetaryAmount;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@Builder(toBuilder = true)
public class Meal {

    private final String category;
    private final String name;
    private final LocalDate day;
    private final List<String> criteria;
    private final Map<Role, MonetaryAmount> price;

}

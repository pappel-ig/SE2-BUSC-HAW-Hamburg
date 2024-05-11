package de.haw_hamburg.mensamatch.adapter.persistence.meal.model;

import de.haw_hamburg.mensamatch.domain.meal.model.Meal;
import de.haw_hamburg.mensamatch.domain.meal.model.Role;
import lombok.*;
import org.javamoney.moneta.Money;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Document
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MealDao {

    private String category;
    private String name;
    private LocalDate day;
    @Indexed
    private List<String> criteria;
    private Map<String, String> price;

    public static MealDao from(Meal meal) {
        return MealDao.builder()
                .category(meal.getCategory())
                .day(meal.getDay())
                .name(meal.getName())
                .price(meal.getPrice().entrySet().stream().collect(Collectors.toMap(t -> t.getKey().toString(), t -> t.getValue().toString())))
                .criteria(meal.getCriteria())
                .build();
    }

    public Meal toMeal() {
        return Meal.builder()
                .category(getCategory())
                .day(getDay())
                .name(getName())
                .price(price.entrySet().stream().collect(Collectors.toMap(t -> Role.valueOf(t.getKey()), t -> Money.parse(t.getValue()))))
                .criteria(getCriteria())
                .build();
    }
}

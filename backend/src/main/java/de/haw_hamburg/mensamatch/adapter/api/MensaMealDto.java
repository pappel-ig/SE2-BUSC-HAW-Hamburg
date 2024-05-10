package de.haw_hamburg.mensamatch.adapter.api;

import de.haw_hamburg.mensamatch.domain.model.Meal;
import de.haw_hamburg.mensamatch.domain.model.Role;
import lombok.*;
import org.javamoney.moneta.Money;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.money.MonetaryAmount;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MensaMealDto {

    private String category;
    private String name;
    private LocalDate day;
    private List<String> criteria;
    private Map<String, String> price;

    public static MensaMealDto from(Meal meal) {
        return MensaMealDto.builder()
                .category(meal.getCategory())
                .day(meal.getDay())
                .name(meal.getName())
                .price(meal.getPrice().entrySet().stream().collect(Collectors.toMap(t -> t.getKey().toString(), t -> t.getValue().toString())))
                .criteria(meal.getCriteria())
                .build();
    }
}

package de.haw_hamburg.mensamatch.adapter.api.meal.model;

import de.haw_hamburg.mensamatch.domain.meal.model.Meal;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
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

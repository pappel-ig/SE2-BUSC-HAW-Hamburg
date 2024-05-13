package de.haw_hamburg.mensamatch.adapter.rest.meal.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import de.haw_hamburg.mensamatch.domain.meal.model.Meal;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@JacksonXmlRootElement(localName = "openmensa")
public class OpenMensaDto {

    @JacksonXmlProperty(localName = "canteen")
    CanteenDto canteen;

    public List<Meal> toMeals() {
        List<Meal> meals = new ArrayList<>();
        for (DayDto day : canteen.day) {
            for (CategoryDto category : day.category) {
                for (MealDto meal : category.meal) {
                    meals.add(Meal.builder()
                            .category(category.category)
                            .name(meal.name)
                            .day(LocalDate.parse(day.date))
                            .price(meal.toPriceMap())
                            .criteria(meal.getNote())
                            .build());
                }
            }
        }
        return meals;
    }
}

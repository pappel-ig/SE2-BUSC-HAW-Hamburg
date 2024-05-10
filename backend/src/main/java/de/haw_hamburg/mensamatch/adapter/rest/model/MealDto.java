package de.haw_hamburg.mensamatch.adapter.rest.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import de.haw_hamburg.mensamatch.domain.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
public class MealDto {
    @JacksonXmlProperty(localName = "name")
    String name;

    @JacksonXmlElementWrapper(localName = "note", useWrapping = false)
    List<String> note;

    @JacksonXmlElementWrapper(localName = "price", useWrapping = false)
    List<PriceDto> price;

    public Map<Role, MonetaryAmount> toPriceMap() {
        Map<Role, MonetaryAmount> priceMap = new HashMap<>();
        for (PriceDto priceDto : price) {
            Role role = switch (priceDto.getRole()) {
                case "employee" -> Role.INTERN;
                case "student" -> Role.STUD;
                case "other" -> Role.EXTERN;
                default -> throw new IllegalStateException("Unexpected value: " + priceDto.getRole());
            };
            priceMap.put(role, Money.of(Double.parseDouble(priceDto.getPrice()), "EUR"));
        }
        return priceMap;
    }

}

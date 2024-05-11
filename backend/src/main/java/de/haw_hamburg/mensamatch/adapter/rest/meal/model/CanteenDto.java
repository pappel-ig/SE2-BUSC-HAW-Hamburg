package de.haw_hamburg.mensamatch.adapter.rest.meal.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CanteenDto {

    @JacksonXmlProperty(localName = "day")
    DayDto day;

}

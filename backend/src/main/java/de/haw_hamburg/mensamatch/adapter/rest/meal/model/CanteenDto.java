package de.haw_hamburg.mensamatch.adapter.rest.meal.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CanteenDto {

    @JacksonXmlElementWrapper(localName = "day", useWrapping = false)
    List<DayDto> day;

}

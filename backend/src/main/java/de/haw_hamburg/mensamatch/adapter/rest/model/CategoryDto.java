package de.haw_hamburg.mensamatch.adapter.rest.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CategoryDto {

    @JacksonXmlProperty(localName = "name", isAttribute = true)
    String category;

    @JacksonXmlElementWrapper(localName = "meal", useWrapping = false)
    List<MealDto> meal;

}

package de.haw_hamburg.mensamatch.adapter.rest.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class DayDto {

    @JacksonXmlProperty(localName = "date", isAttribute = true)
    String date;

    @JacksonXmlElementWrapper(localName = "category", useWrapping = false)
    List<CategoryDto> category;

}

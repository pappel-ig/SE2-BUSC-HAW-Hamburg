package de.haw_hamburg.mensamatch.adapter.rest.meal.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PriceDto {

    @JacksonXmlProperty(isAttribute = true, localName = "role")
    String role;

    @JacksonXmlText
    String price;

}

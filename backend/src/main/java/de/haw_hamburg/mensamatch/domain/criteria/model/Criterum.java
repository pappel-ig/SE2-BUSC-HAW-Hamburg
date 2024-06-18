package de.haw_hamburg.mensamatch.domain.criteria.model;

import lombok.Getter;

@Getter
public enum Criterum {

    VEGAN("Vegan"),
    VEGETARISCH("Vegetarisch"),

    GEFLÜGEL("Geflügel"),
    RIND("Rind"),
    SCHWEIN("Schwein"),
    FISCH("Fisch"),
    LAMM("Lamm"),

    ANTIOXIDATIONSMITTEL("mit Antioxidationsmittel"),
    GESCHMACKSVERSTÄRKER("mit Geschmacksverstärker"),
    KONSERVIERUNGSTOFF("mit Konservierungsstoff"),
    PHOSPHAT("mit Phosphat"),
    SÜSSUNGSMITTEL("mit Süßungsmittel"),
    GESCHWAERZT("Geschwärzt"),
    SCHWEFELDIOXID_UND_SULFITE(" Schwefeldioxid und Sulfite (Konzentration über 10mg/kg oder 10mg/l)"),

    SOJA("Soja und Sojaerzeugnisse"),
    SELLERIE("Sellerie und Sellerieerzeugnisse"),
    SENF("Senf und Senferzeugnisse"),
    EI("Ei und Eierzeugnisse"),
    MILCH("Milch und Milcherzeugnisse (einschließlich Laktose)"),

    LAKTOSEFREI("enthält keine laktosehaltigen Lebensmittel"),
    NEU("NEU"),
    VITAL("mensaVital");

    public String text;

    Criterum(String text) {
        this.text = text;
    }
}

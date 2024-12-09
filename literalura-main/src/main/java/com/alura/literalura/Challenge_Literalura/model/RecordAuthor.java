package com.alura.literalura.Challenge_Literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RecordAuthor(
        @JsonAlias("name") String name,
        @JsonAlias("birth_year") int birth_year,
        @JsonAlias("death_year") int death_year) {
}

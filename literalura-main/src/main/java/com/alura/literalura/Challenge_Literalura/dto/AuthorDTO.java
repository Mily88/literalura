package com.alura.literalura.Challenge_Literalura.dto;

import jakarta.persistence.Column;

public record AuthorDTO(
        Long id,

        String name,

        int birth_year,

        int death_year
) {
}

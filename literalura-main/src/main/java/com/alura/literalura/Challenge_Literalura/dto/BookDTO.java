package com.alura.literalura.Challenge_Literalura.dto;

import java.util.List;

public record BookDTO (
        Long id,
        String title,
        AuthorDTO author,
        String languages,
        Long download_count
){
}

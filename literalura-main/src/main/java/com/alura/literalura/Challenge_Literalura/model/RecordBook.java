package com.alura.literalura.Challenge_Literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RecordBook(
        @JsonAlias("id") Long gutendex_id,
        @JsonAlias("title") String title,
        @JsonAlias("authors") List<RecordAuthor> authors,
        @JsonAlias("languages") List<String> languages,
        @JsonAlias("download_count") Integer download_count) {
}

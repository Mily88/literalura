package com.alura.literalura.Challenge_Literalura.model;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.List;


@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Long gutendex_id;

    @Column(unique = true)
    private String title;

    private String languages;

    @ManyToOne
    private Author author;

    private Integer download_count;

    public Book(){}

    public Book(RecordBook recordBook){
        this.title = recordBook.title();
        this.gutendex_id = recordBook.gutendex_id();
        this.download_count = recordBook.download_count();

        // Turning a list into a String
        this.languages = String.join(",", recordBook.languages());


    }

    public Long getId() {
        return Id;
    }

    public Long getGutendex_id() {
        return gutendex_id;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getLanguages() {
        return Arrays.asList(this.languages.split(","));
    }

    public Author getAuthor() {
        return author;
    }

    public Integer getDownload_count() {
        return download_count;
    }

    public void setLanguages(List<String> languages) {
        this.languages = String.join(",", languages);
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setDownload_count(Integer download_count) {
        this.download_count = download_count;
    }

    @Override
    public String toString() {
        return """
                **************************************************
                *                      LIBRO                     *
                **************************************************
                Título: %s
                Autor: %s
                Idioma: %s
                N° Descargas: %s""".formatted(this.title, this.author.getName(), this.languages, this.download_count.toString());// + "\n";
    }
}

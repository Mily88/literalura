package com.alura.literalura.Challenge_Literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private int birth_year;

    private int death_year;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> books;


    public Author(){}

    public Author(RecordAuthor recordAuthor){
        this.name = recordAuthor.name();
        this.birth_year = recordAuthor.birth_year();
        this.death_year = recordAuthor.death_year();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(int birth_year) {
        this.birth_year = birth_year;
    }

    public int getDeath_year() {
        return death_year;
    }

    public void setDeath_year(int death_year) {
        this.death_year = death_year;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        StringBuilder stringBooks = new StringBuilder();
        for (Book book : this.books) {
            stringBooks.append(book.getTitle()).append(" ; ");
        }
        return """
               **************************************************
               *                      AUTOR                     *
               **************************************************
               Autor: %s
               Fecha de Nacimiento: %s
               Fecha de Fallecimiento: %s
               Libros: %s
               """.formatted(this.name, this.birth_year, this.death_year, stringBooks.toString()) + "\n";
    }
}

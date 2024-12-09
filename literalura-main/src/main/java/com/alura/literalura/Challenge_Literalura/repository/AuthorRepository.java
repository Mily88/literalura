package com.alura.literalura.Challenge_Literalura.repository;

import com.alura.literalura.Challenge_Literalura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository  extends JpaRepository<Author, Long> {

    Author findByNameContainingIgnoreCase(String name);

    @Query("SELECT a FROM Author a WHERE a.birth_year <= :year AND (a.death_year > :year OR a.death_year IS NULL)")
    List<Author>  findAuthorsAliveInYear(int year);
}

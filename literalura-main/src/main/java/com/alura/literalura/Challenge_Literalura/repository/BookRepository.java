package com.alura.literalura.Challenge_Literalura.repository;

import com.alura.literalura.Challenge_Literalura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByLanguagesContainingIgnoreCase(String lenguage);

    Optional<Book> findByTitleIgnoreCase(String title);

    @Query("SELECT b FROM Book as b ORDER BY b.download_count DESC LIMIT 5")
    List<Book> findTop5();
}

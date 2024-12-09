package com.alura.literalura.Challenge_Literalura.service;

import com.alura.literalura.Challenge_Literalura.model.Book;
import com.alura.literalura.Challenge_Literalura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public List<Book>  getAllBooks(){
        return repository.findAll();
    }

    public Optional<Book> getBookByTitle(String title){
        return repository.findByTitleIgnoreCase(title);

    }

    public List<Book> findTop5(){
        return repository.findTop5();
    }

    public List<Book> getBooksByLanguage(String language){
        return repository.findByLanguagesContainingIgnoreCase(language);
    }

    public void saveBook(Book newBook){
        repository.save(newBook);
    }

}

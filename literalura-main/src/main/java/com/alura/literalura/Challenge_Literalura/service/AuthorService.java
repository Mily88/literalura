package com.alura.literalura.Challenge_Literalura.service;

import com.alura.literalura.Challenge_Literalura.model.Author;
import com.alura.literalura.Challenge_Literalura.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository repository;

    public List<Author> getAllAuthors(){
        return repository.findAll();
    }

    public Author getAuthorByName(String name){
        return repository.findByNameContainingIgnoreCase(name);
    }

    public List<Author> getAuthorAliveInYear(int year){
        return repository.findAuthorsAliveInYear(year);
    }

    public void saveAuthor(Author newAuthor){
        repository.save(newAuthor);
    }
}

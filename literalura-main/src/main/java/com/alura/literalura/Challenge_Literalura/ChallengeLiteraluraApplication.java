package com.alura.literalura.Challenge_Literalura;

import com.alura.literalura.Challenge_Literalura.main.Main;
import com.alura.literalura.Challenge_Literalura.service.AuthorService;
import com.alura.literalura.Challenge_Literalura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeLiteraluraApplication implements CommandLineRunner{

	@Autowired
	private AuthorService authorService;

	@Autowired
	private BookService bookService;

	public static void main(String[] args) {

		SpringApplication.run(ChallengeLiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(bookService, authorService);
		main.showMenu();
	}

}

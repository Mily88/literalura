package com.alura.literalura.Challenge_Literalura.main;

import com.alura.literalura.Challenge_Literalura.model.Author;
import com.alura.literalura.Challenge_Literalura.model.Book;
import com.alura.literalura.Challenge_Literalura.model.RecordBook;
import com.alura.literalura.Challenge_Literalura.model.Records;
import com.alura.literalura.Challenge_Literalura.service.AuthorService;
import com.alura.literalura.Challenge_Literalura.service.BookService;
import com.alura.literalura.Challenge_Literalura.service.ConsumoAPI;
import com.alura.literalura.Challenge_Literalura.service.ConvertData;
import com.alura.literalura.Challenge_Literalura.utility.Screens;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private Scanner myScanner = new Scanner(System.in);
    private BookService bookService;
    private AuthorService authorService;
    private ConvertData convertData = new ConvertData();
    private ConsumoAPI apiCaller = new ConsumoAPI();

    private final String URL_BASE = "https://gutendex.com/books/";

    public Main(BookService bookService, AuthorService authorService){
        this.bookService = bookService;
        this.authorService = authorService;
    }

    public void showMenu() {

        Screens.screenWelcome();
        var option = -1;
        while (option != 0) {
            Screens.screenOption();


//            var menu = """
//
//
//                    1 - Buscar Libro por titulo.
//                    2 - Listar libros registrados.
//                    3 - Listar autores registrados.
//                    4 - Listar autores vivos en un determinado año.
//                    5 - Listar libros por idioma.
//                    6 - Listar Top 5 libros en base a descargas acumuladas.
//
//                    0 - Salir
//                    """;

//            System.out.println(menu);
            option = myScanner.nextInt();
            myScanner.nextLine();


            switch (option) {
                case 1:
                    addBook();
                    waitingForInterection();
                    break;
                case 2:
                    showRecordedBooks();
                    waitingForInterection();
                    break;
                case 3:
                    showRecordedAuthors();
                    waitingForInterection();
                    break;
                case 4:
                    showAuthorsAliveByYear();
                    waitingForInterection();
                    break;
                case 5:
                    showBooksByLeanguage();
                    waitingForInterection();
                    break;
                case 6:
                    showTop5Books();
                    waitingForInterection();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    Screens.screenGoodBye();
                    break;
                default:
                    Screens.screenInvalidOption();
            }

        }
    }


    private void addBook(){

        RecordBook recordBook = searchBookData();

        if(recordBook != null){
            Optional<Book> dbBook = bookService.getBookByTitle(recordBook.title());

            if (dbBook.isPresent()){
                System.out.printf("El libro %s ya existe in la base de datos. ", recordBook.title());
            }
            else {
                if(recordBook.authors() != null){
                    Author author = authorService.getAuthorByName(recordBook.authors().get(0).name());
                    if(author == null){
                        author = new Author(recordBook.authors().get(0));
                    }

                    authorService.saveAuthor(author);
                    Book book = new Book(recordBook);
                    book.setAuthor(author);
                    bookService.saveBook(book);

                    System.out.println(book);
                    Screens.screenSeparation();
                }
                else{
                    System.out.println("Faltan datos de autor");
                }
            }
        }
    }

    private RecordBook searchBookData(){
        System.out.println("Ingrese el titulo del libro que deseas buscar");
        String bookTitle = myScanner.nextLine();

        System.out.println("==============================================" + "\n");
        var json = apiCaller.obtenerDatos(URL_BASE + "?search=" + bookTitle.replace(" ", "+") );
        Records results = convertData.obtenerDatos(json, Records.class);

        Optional<RecordBook> optionalRecordBook = results.books().stream()
                .filter(b -> b.title().toLowerCase().contains(bookTitle.toLowerCase()))
                .findFirst();
        if (optionalRecordBook.isPresent()) {
            return optionalRecordBook.get();
        } else {
            System.out.println("No se encontró el libro.");
            return null;
        }
    }

    private void showRecordedBooks(){
        List<Book> books = bookService.getAllBooks();
        books.forEach(System.out::println);

    }

    private void showRecordedAuthors(){
        List<Author> authors = authorService.getAllAuthors();
        authors.forEach(System.out::println);

    }

    private void showAuthorsAliveByYear(){

        boolean flag = true;
        System.out.println("Ingrese un año para buscar.");
        var stringYear = myScanner.nextLine();
        int year = -5000;
        while (flag)
            try{
                year = Integer.parseInt(stringYear);
                flag = false;
            } catch (NumberFormatException e) {
                Screens.screenInvalidInput();
                System.out.println("Ingrese un año para buscar.");
                stringYear = myScanner.nextLine();
            }


        List<Author> authors = authorService.getAuthorAliveInYear(year);
        authors.forEach(System.out::println);
    }

    private void showBooksByLeanguage(){
        Screens.screenLenguageOpt();

        var option = -1;
        option = myScanner.nextInt();
        myScanner.nextLine();

        switch (option) {
            case 1:
                BooksByLeanguage("es");
                break;
            case 2:
                BooksByLeanguage("en");

                break;
            case 3:
                BooksByLeanguage("fr");
                break;
            case 4:
                BooksByLeanguage("it");
                break;
            case 0:
                break;
            default:
                Screens.screenInvalidOption();
        }
    }

    private void waitingForInterection(){
        System.out.println();
        System.out.println("Presione enter 2 veces para volver al menu inicial");
        myScanner.nextLine();
    }

    private void BooksByLeanguage(String lenguage){
        List<Book> books = bookService.getBooksByLanguage(lenguage);
        if(books.isEmpty()){
            System.out.printf("No hay libros registrados en idioma solicitado %s.%n", lenguage);
        }
        else{
            books.forEach(System.out::println);
        }
    }

    private void showTop5Books(){
        bookService.findTop5().forEach(System.out::println);
    }

}

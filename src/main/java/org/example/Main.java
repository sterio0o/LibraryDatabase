package org.example;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Book book;
        String url = ""; // localhost or url
        String user = "root";
        String password = ""; // Необходимо ввести пароль от БД

        BookRepository bookRepository = new BookRepository(url, user, password);
        bookRepository.connect();

        System.out.println("Добро пожаловать в библиотеку!");
        System.out.println("Выберите что хотите сделать: ");
        System.out.println("1. Получить книгу");
        System.out.println("2. Получить список всех книг");
        System.out.println("3. Добавить книгу");
        System.out.println("4. Удалить книгу");
        System.out.println("5. Экспортировать в JSON");
        System.out.println("6. Импортировать данные в БД");

        Scanner scan = new Scanner(System.in);
        int x = scan.nextInt();
        scan.nextLine();
        switch (x) {
            case 1:
                System.out.println("Введите название: ");
                String title = scan.nextLine();

                System.out.println("Введите автора: ");
                String author = scan.nextLine();

                book = bookRepository.getBook(author, title);
                System.out.println(book.getId() + " | " +
                        book.getTitle() + " | " +
                        book.getAuthor() + " | " +
                        book.getPage()
                );
                break;
            case 2:
                List<Book> listBook = bookRepository.getAllBooks();
                for (Book book1 : listBook) {
                    System.out.println(book1.getId() + " | " +
                            book1.getTitle() + " | " +
                            book1.getAuthor() + " | " +
                            book1.getPage()
                    );
                }
                break;
            case 3:
                System.out.println("Введите название:");
                String titleSet = scan.nextLine();

                System.out.println("Введите автора:");
                String authorSet = scan.nextLine();

                System.out.println("Введите кол-во страниц:");
                int page = scan.nextInt();
                scan.nextLine();

                bookRepository.setBook(titleSet, authorSet, page);
                break;
            case 4:
                System.out.println("Введите название: ");
                String deleteTitle = scan.nextLine();
                scan.nextLine();

                System.out.println("Введите автора: ");
                String deleteAuthor = scan.nextLine();
                scan.nextLine();

                bookRepository.deleteBook(deleteTitle, deleteAuthor);
            case 5:
                List<Book> books = bookRepository.getAllBooks();
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonDataService jsonDataService = new JsonDataService(gson, bookRepository);
                jsonDataService.export(books);
                break;
            case 6:
                Gson gson1 = new Gson();
                JsonDataService jsonDataService1 = new JsonDataService(gson1, bookRepository);
                jsonDataService1.importer();
                break;
            default:
                System.out.println("Неправильно введеные данные");
                break;
        }
    }
}

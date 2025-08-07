package org.example;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

public class JsonDataService {
    private final Gson gson;
    private final BookRepository bookRepository;

    public JsonDataService(Gson gson, BookRepository bookRepository) {
        this.gson = gson;
        this.bookRepository = bookRepository;
    }

    public void export(List<Book> bookList) {
        String gsonStr = gson.toJson(bookList);
        try (FileWriter fileWriter = new FileWriter("booksExport.json")) {
            fileWriter.write(gsonStr);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void importer() {
        Path filePath = Paths.get("booksImport.json");
        try (Reader reader = new FileReader(filePath.toFile())) {
            Book[] books = gson.fromJson(reader, Book[].class);
            for (Book book : books) {
                bookRepository.setBook(
                        book.getTitle(), book.getAuthor(), book.getPage()
                );
            }
        }
        catch (IOException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}

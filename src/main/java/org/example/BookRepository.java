package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    String url;
    String user;
    String password;

    public Connection connection;
    public Statement statement;

    public BookRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void connect() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement();
    }

    public void setBook(String title, String author, int page) throws SQLException {
        if (!title.isEmpty() && !author.isEmpty() && page > 0) {
            String sql = "INSERT IGNORE INTO books (title, author, page) VALUES (?, ?, ?);";
            try (PreparedStatement stml = connection.prepareStatement(sql)) {
                stml.setString(1, title);
                stml.setString(2, author);
                stml.setInt(3, page);
                stml.executeUpdate();
            }
        }
    }

    public Book getBook(String author, String title) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM books " +
                "WHERE author = '" + author + "' AND " + "title = '" + title + "';"
        );

        Book book = new Book();
        while (resultSet.next()) {
            book.setId(resultSet.getInt("id"));
            book.setTitle(resultSet.getString("title"));
            book.setAuthor(resultSet.getString("author"));
            book.setPage(resultSet.getInt("page"));
        }

        return book;
    }

    public List<Book> getAllBooks() throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * FROM books ORDER BY id ASC;");
        List<Book> listBook = new ArrayList<>();
        while (result.next()) {
            Book book = new Book();
            book.setId(result.getInt("id"));
            book.setTitle(result.getString("title"));
            book.setAuthor(result.getString("author"));
            book.setPage(result.getInt("page"));
            listBook.add(book);
        }

        return listBook;
    }

    public void deleteBook(String title, String author) throws SQLException {
        statement.executeUpdate("DELETE FROM books " +
                "WHERE title = '" + title +
                "' AND author = '" + author + "');"
        );
    }
}


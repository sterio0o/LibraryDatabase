package org.example;

public class Book {
    int id;
    String author;
    String title;
    int page;

    public Book() {}

    public Book(int id, String author, String title, int page) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.page = page;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}


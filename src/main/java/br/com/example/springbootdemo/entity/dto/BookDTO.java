package br.com.example.springbootdemo.entity.dto;

public class BookDTO {

    private String name;
    private String year;
    private double edition;
    private String author;

    public BookDTO() {
    }

    public BookDTO(String name, String year, double edition, String author) {
        this.name = name;
        this.year = year;
        this.edition = edition;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public double getEdition() {
        return edition;
    }

    public void setEdition(double edition) {
        this.edition = edition;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

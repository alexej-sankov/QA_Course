package com.telran;

public class Product {

    private String title;
    private String link;
    private double price;

    public Product(String title, String link, double price) {
        this.title = title;
        this.link = link;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", price=" + price +
                '}';
    }
}

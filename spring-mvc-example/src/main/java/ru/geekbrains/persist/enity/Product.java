package ru.geekbrains.persist.enity;

public class Product {

    private Long id;

    private String title;

    private double cost;

    private String description;


    public Product() {
    }

    public Product(Long id, String title, double cost, String description) {
        this.id = id;
        this.title = title;
        this.cost = cost;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

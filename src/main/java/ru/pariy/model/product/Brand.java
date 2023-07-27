package ru.pariy.model.product;

public class Brand {
    private int id;
    private String name;


    public Brand(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
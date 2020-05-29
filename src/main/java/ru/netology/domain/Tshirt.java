package ru.netology.domain;

public class Tshirt extends Product{
    public String brand;

    public Tshirt(){
    }

    public Tshirt(int id, String name, int price, String brand) {
        super(id, name, price);
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}

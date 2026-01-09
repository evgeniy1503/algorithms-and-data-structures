package ru.prokhorov.task_12_2.items.impl;

import ru.prokhorov.task_12_2.items.Item;
import ru.prokhorov.task_12_2.visitor.Visitor;

/**
 * Реализация {@link Item} для {@link Laptop}
 *
 * @author Evgeniy_Prohorov
 */
public class Laptop implements Item {

    private String model;
    private String brand;
    private double price;

    public Laptop(String model, String brand, double price) {
        this.model = model;
        this.brand = brand;
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitLaptop(this);
    }
}

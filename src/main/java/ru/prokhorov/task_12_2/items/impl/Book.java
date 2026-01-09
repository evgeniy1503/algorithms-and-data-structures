package ru.prokhorov.task_12_2.items.impl;

import ru.prokhorov.task_12_2.items.Item;
import ru.prokhorov.task_12_2.visitor.Visitor;

/**
 * Реализация {@link Item} для {@link Book}
 *
 * @author Evgeniy_Prohorov
 */

public class Book implements Item {

    private String title;
    private String author;
    private double price;

    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitBook(this);
    }
}

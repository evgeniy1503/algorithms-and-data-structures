package ru.prokhorov.task_12_2.items.impl;

import ru.prokhorov.task_12_2.items.Item;
import ru.prokhorov.task_12_2.visitor.Visitor;

/**
 * Реализация {@link Item} для {@link VideoGame}
 *
 * @author Evgeniy_Prohorov
 */
public class VideoGame implements Item {

    private String name;
    private String platform;
    private double price;

    public VideoGame(String name, String platform, double price) {
        this.name = name;
        this.platform = platform;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getPlatform() {
        return platform;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitVideoGame(this);
    }
}

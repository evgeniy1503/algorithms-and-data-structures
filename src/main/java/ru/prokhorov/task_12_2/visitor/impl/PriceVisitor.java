package ru.prokhorov.task_12_2.visitor.impl;

import ru.prokhorov.task_12_2.items.impl.Book;
import ru.prokhorov.task_12_2.items.impl.Laptop;
import ru.prokhorov.task_12_2.items.impl.VideoGame;
import ru.prokhorov.task_12_2.visitor.Visitor;

/**
 * Реализация посетителя по Цене
 *
 * @author Evgeniy_Prohorov
 */

public class PriceVisitor implements Visitor {

    private double totalPrice = 0;

    @Override
    public void visitBook(Book book) {
        totalPrice += book.getPrice();
        System.out.println("Цена книги '" + book.getTitle() + "': $" + book.getPrice());
    }

    @Override
    public void visitVideoGame(VideoGame videoGame) {
        totalPrice += videoGame.getPrice();
        System.out.println("Цена игры '" + videoGame.getName() + "': $" + videoGame.getPrice());
    }

    @Override
    public void visitLaptop(Laptop laptop) {
        totalPrice += laptop.getPrice();
        System.out.println("Цена ноутбука '" + laptop.getModel() + "': $" + laptop.getPrice());
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}

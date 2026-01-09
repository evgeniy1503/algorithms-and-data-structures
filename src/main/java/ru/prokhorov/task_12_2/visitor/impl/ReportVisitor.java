package ru.prokhorov.task_12_2.visitor.impl;

import ru.prokhorov.task_12_2.items.impl.Book;
import ru.prokhorov.task_12_2.items.impl.Laptop;
import ru.prokhorov.task_12_2.items.impl.VideoGame;
import ru.prokhorov.task_12_2.visitor.Visitor;

/**
 * Реализация посетителя по сбору информации по продукту
 *
 * @author Evgeniy_Prohorov
 */
public class ReportVisitor implements Visitor {

    @Override
    public void visitBook(Book book) {
        System.out.println("Книга: '" + book.getTitle() + "' от " + book.getAuthor() +
                ", цена: $" + book.getPrice());
    }

    @Override
    public void visitVideoGame(VideoGame videoGame) {
        System.out.println("Видеоигра: '" + videoGame.getName() + "' для " +
                videoGame.getPlatform() + ", цена: $" + videoGame.getPrice());
    }

    @Override
    public void visitLaptop(Laptop laptop) {
        System.out.println("Ноутбук: " + laptop.getBrand() + " " + laptop.getModel() +
                ", цена: $" + laptop.getPrice());
    }
}

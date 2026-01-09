package ru.prokhorov.task_12_2.visitor;

import ru.prokhorov.task_12_2.items.impl.Book;
import ru.prokhorov.task_12_2.items.impl.Laptop;
import ru.prokhorov.task_12_2.items.impl.VideoGame;

/**
 * Интерфейс посетителя
 *
 * @author Evgeniy_Prohorov
 */
public interface Visitor {

    void visitBook(Book book);
    void visitVideoGame(VideoGame videoGame);
    void visitLaptop(Laptop laptop);
}

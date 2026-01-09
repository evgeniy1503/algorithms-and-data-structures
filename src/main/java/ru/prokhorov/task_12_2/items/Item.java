package ru.prokhorov.task_12_2.items;

import ru.prokhorov.task_12_2.visitor.Visitor;

/**
 * Интерфейс для товаров
 *
 * @author Evgeniy_Prohorov
 */
public interface Item {

    void accept(Visitor visitor);

}

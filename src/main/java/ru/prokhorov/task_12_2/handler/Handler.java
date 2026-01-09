package ru.prokhorov.task_12_2.handler;

import ru.prokhorov.task_12_2.Order;

/**
 * Базовый обработчик
 *
 * @author Evgeniy_Prohorov
 */
abstract public class Handler {
    protected Handler next;

    public Handler setNext(Handler next) {
        this.next = next;
        return next;
    }

    public boolean handle(Order order) {
        if (check(order)) {
            System.out.println("✓ Проверка " + getClass().getSimpleName() + " пройдена");
            if (next != null) {
                return next.handle(order);
            }
            return true;
        } else {
            System.out.println("✗ Проверка " + getClass().getSimpleName() + " не пройдена");
            return false;
        }
    }

    protected abstract boolean check(Order order);
}

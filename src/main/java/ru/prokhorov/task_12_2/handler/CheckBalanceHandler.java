package ru.prokhorov.task_12_2.handler;

import ru.prokhorov.task_12_2.Order;

/**
 * Обработчик проверки баланса
 *
 * @author Evgeniy_Prohorov
 */
public class CheckBalanceHandler extends Handler {
    @Override
    protected boolean check(Order order) {
        // Проверяем, хватает ли средств (для упрощения - всегда хватает)
        return true;
    }
}

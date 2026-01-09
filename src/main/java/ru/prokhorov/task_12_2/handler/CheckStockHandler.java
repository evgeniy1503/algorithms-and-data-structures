package ru.prokhorov.task_12_2.handler;

import ru.prokhorov.task_12_2.Order;

/**
 * Конкретные обработчики по наличию товара.
 *
 * @author Evgeniy_Prohorov
 */
public class CheckStockHandler extends Handler {
    @Override
    protected boolean check(Order order) {
        // Всегда в наличии для упрощения
        return order.getItems().size() > 0;
    }
}

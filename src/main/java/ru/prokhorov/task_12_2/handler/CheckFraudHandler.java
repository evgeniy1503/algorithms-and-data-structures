package ru.prokhorov.task_12_2.handler;

import ru.prokhorov.task_12_2.Order;

/**
 * @author Evgeniy_Prohorov
 */
public class CheckFraudHandler extends Handler {
    @Override
    protected boolean check(Order order) {
        // Проверяем на подозрительность (для упрощения - всегда не подозрительно)
        return !order.getItems().isEmpty() && order.getTotalPrice() < 10000;
    }
}

package ru.prokhorov.task_12_2.processor;

import ru.prokhorov.task_12_2.Order;

/**
 * Базовый класс для обработки заказа
 *
 * @author Evgeniy_Prohorov
 */
public abstract class OrderProcessor {
    protected Order order;

    // Финальный метод шаблона
    public final void processOrder() {
        System.out.println("\n=== Начало обработки заказа ===");

        if (!validate()) {
            System.out.println("❌ Заказ не прошел валидацию!");
            return;
        }

        calculatePrice();
        pay();
        notification();
        System.out.println("✅ Заказ успешно обработан!");
    }

    protected abstract boolean validate();
    protected abstract void calculatePrice();
    protected abstract void pay();

    protected void notification() {
        System.out.println("Отправлено стандартное уведомление");
    }
}
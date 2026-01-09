package ru.prokhorov.task_12_2.processor;

import ru.prokhorov.task_12_2.Order;

/**
 * Для обработки онлайн заказов
 *
 * @author Evgeniy_Prohorov
 */

public class DigitalOrderProcessor extends OrderProcessor {

    public DigitalOrderProcessor(Order order) {
        this.order = order;
    }

    @Override
    protected boolean validate() {
        System.out.println("Проверка email для цифрового заказа...");
        return order.getCustomerEmail() != null && !order.getCustomerEmail().isEmpty();
    }

    @Override
    protected void calculatePrice() {
        // Только цена товара
        double total = order.getTotalPrice();
        order.setFinalPrice(total);
        System.out.println("Цена товара: $" + total);
        System.out.println("Доставка: $0.00 (цифровой товар)");
        System.out.println("Итоговая цена: $" + order.getFinalPrice());
    }

    @Override
    protected void pay() {
        System.out.println("Оплата онлайн...");
        System.out.println("Списано: $" + order.getFinalPrice());
    }

    @Override
    protected void notification() {
        System.out.println("Ссылка для скачивания отправлена на email: " + order.getCustomerEmail());
    }
}
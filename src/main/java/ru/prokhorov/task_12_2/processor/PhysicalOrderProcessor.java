package ru.prokhorov.task_12_2.processor;

import ru.prokhorov.task_12_2.Order;

/**
 * Физическая обработка заказа
 *
 * @author Evgeniy_Prohorov
 */
public class PhysicalOrderProcessor extends OrderProcessor {
    private static final double SHIPPING_COST = 9.99;

    public PhysicalOrderProcessor(Order order) {
        this.order = order;
    }

    @Override
    protected boolean validate() {
        System.out.println("Проверка адреса для физического заказа...");
        return order.getShippingAddress() != null && !order.getShippingAddress().isEmpty();
    }

    @Override
    protected void calculatePrice() {
        // Цена товара + доставка
        double subtotal = order.getTotalPrice();
        double total = subtotal + SHIPPING_COST;
        order.setFinalPrice(total);
        System.out.println("Цена товара: $" + subtotal);
        System.out.println("Доставка: $" + SHIPPING_COST);
        System.out.println("Итоговая цена: $" + order.getFinalPrice());
    }

    @Override
    protected void pay() {
        System.out.println("Оплата при получении...");
        System.out.println("К оплате при получении: $" + order.getFinalPrice());
    }

    @Override
    protected void notification() {
        System.out.println("Товар отправлен по адресу: " + order.getShippingAddress());
        System.out.println("Ожидаемая дата доставки: 3-5 рабочих дней");
    }
}
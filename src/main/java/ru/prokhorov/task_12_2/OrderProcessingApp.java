package ru.prokhorov.task_12_2;

import ru.prokhorov.task_12_2.handler.CheckBalanceHandler;
import ru.prokhorov.task_12_2.handler.CheckFraudHandler;
import ru.prokhorov.task_12_2.handler.CheckStockHandler;
import ru.prokhorov.task_12_2.handler.Handler;
import ru.prokhorov.task_12_2.items.Item;
import ru.prokhorov.task_12_2.items.impl.Book;
import ru.prokhorov.task_12_2.items.impl.Laptop;
import ru.prokhorov.task_12_2.items.impl.VideoGame;
import ru.prokhorov.task_12_2.processor.DigitalOrderProcessor;
import ru.prokhorov.task_12_2.processor.OrderProcessor;
import ru.prokhorov.task_12_2.processor.PhysicalOrderProcessor;
import ru.prokhorov.task_12_2.visitor.impl.PriceVisitor;
import ru.prokhorov.task_12_2.visitor.impl.ReportVisitor;

/**
 * Главный класс
 *
 * @author Evgeniy_Prohorov
 */
public class OrderProcessingApp {
    public static void main(String[] args) {
        System.out.println("=== Система обработки заказов ===\n");

        // 1. Создаем заказ с товарами
        Order order = new Order();
        order.addItem(new Book("Война и мир", "Лев Толстой", 25.99));
        order.addItem(new VideoGame("Cyberpunk 2077", "PS5", 59.99));
        order.addItem(new Laptop("MacBook Pro", "Apple", 1999.99));

        // Настройка заказа (цифровой или физический)
        if (args.length > 0 && args[0].equals("physical")) {
            order.setShippingAddress("ул. Пушкина, д. 10, кв. 5");
        } else {
            order.setCustomerEmail("customer@example.com");
        }

        // 2. Visitor: Обработка товаров
        System.out.println("=== Visitor Pattern ===");

        // Отчет по товарам
        ReportVisitor reportVisitor = new ReportVisitor();
        System.out.println("\nОтчет по товарам:");
        for (Item item : order.getItems()) {
            item.accept(reportVisitor);
        }

        // Подсчет цены
        PriceVisitor priceVisitor = new PriceVisitor();
        for (Item item : order.getItems()) {
            item.accept(priceVisitor);
        }
        System.out.println("\nОбщая стоимость товаров: $" + priceVisitor.getTotalPrice());

        // 3. Chain of Responsibility: Предварительные проверки
        System.out.println("\n=== Chain of Responsibility ===");

        Handler chain = new CheckStockHandler();
        chain.setNext(new CheckBalanceHandler())
                .setNext(new CheckFraudHandler());

        boolean checksPassed = chain.handle(order);

        if (!checksPassed) {
            System.out.println("❌ Заказ не прошел предварительные проверки!");
            return;
        }

        System.out.println("✅ Все предварительные проверки пройдены!");

        // 4. Template Method: Обработка заказа
        System.out.println("\n=== Template Method ===");

        OrderProcessor processor;
        if (args.length > 0 && args[0].equals("physical")) {
            processor = new PhysicalOrderProcessor(order);
        } else {
            processor = new DigitalOrderProcessor(order);
        }

        processor.processOrder();

        System.out.println("\n=== Завершение работы системы ===");
    }
}

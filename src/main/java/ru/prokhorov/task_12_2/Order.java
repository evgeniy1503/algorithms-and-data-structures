package ru.prokhorov.task_12_2;

import ru.prokhorov.task_12_2.items.Item;
import ru.prokhorov.task_12_2.visitor.impl.PriceVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Заказ
 *
 * @author Evgeniy_Prohorov
 */
public class Order {
    private List<Item> items = new ArrayList<>();
    private String customerEmail;
    private String shippingAddress;
    private double finalPrice;

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public double getTotalPrice() {
        PriceVisitor visitor = new PriceVisitor();
        for (Item item : items) {
            item.accept(visitor);
        }
        return visitor.getTotalPrice();
    }

    public void setCustomerEmail(String email) {
        this.customerEmail = email;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setShippingAddress(String address) {
        this.shippingAddress = address;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setFinalPrice(double price) {
        this.finalPrice = price;
    }

    public double getFinalPrice() {
        return finalPrice;
    }
}

package ru.prokhorov.pattern1.factory;

/**
 * @author Evgeniy_Prohorov
 */

public class FactoryDemo {
    public static void main(String[] args) {
        TransportFactory factory = new TransportFactory();

        Transport truck = factory.createTransport("truck");
        truck.deliver(); // Доставка груза по дороге

        Transport ship = factory.createTransport("ship");
        ship.deliver(); // Доставка груза по морю
    }
}

package ru.prokhorov.pattern1.factory;

/**
 * @author Evgeniy_Prohorov
 */

public class TransportFactory {
    public Transport createTransport(String type) {
        if (type.equalsIgnoreCase("truck")) {
            return new Truck();
        } else if (type.equalsIgnoreCase("ship")) {
            return new Ship();
        }
        throw new IllegalArgumentException("Неизвестный тип транспорта");
    }
}

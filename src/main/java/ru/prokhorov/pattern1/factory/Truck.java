package ru.prokhorov.pattern1.factory;

/**
 * @author Evgeniy_Prohorov
 */

public class Truck implements Transport{
    @Override
    public void deliver() {
        System.out.println("Доставка груза по дороге");
    }
}

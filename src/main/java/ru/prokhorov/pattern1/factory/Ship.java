package ru.prokhorov.pattern1.factory;

/**
 * @author Evgeniy_Prohorov
 */

public class Ship implements Transport{
    @Override
    public void deliver() {
        System.out.println("Доставка груза по морю");
    }
}

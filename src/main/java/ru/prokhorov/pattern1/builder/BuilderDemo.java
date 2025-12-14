package ru.prokhorov.pattern1.builder;

/**
 * @author Evgeniy_Prohorov
 */

public class BuilderDemo {
    public static void main(String[] args) {

        Computer gamingPC = new Computer.Builder("Intel i7")
                .ram(32)
                .storage(1000)
                .hasGraphicsCard(true)
                .build();

        Computer officePC = new Computer.Builder("Intel i5")
                .ram(16)
                .build();

        Computer basicPC = new Computer.Builder("Intel i3").build();

        System.out.println(gamingPC);
        System.out.println(officePC);
        System.out.println(basicPC);

    }
}

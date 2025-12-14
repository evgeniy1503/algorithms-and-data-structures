package ru.prokhorov.pattern1.builder;

/**
 * @author Evgeniy_Prohorov
 */

public class Computer {
    private String cpu;
    private int ram;
    private int storage;
    private boolean hasGraphicsCard;

    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.hasGraphicsCard = builder.hasGraphicsCard;
    }

    @Override
    public String toString() {
        return String.format("Computer[CPU=%s, RAM=%dGB, Storage=%dGB, Graphics=%s]",
                cpu, ram, storage, hasGraphicsCard ? "Yes" : "No");
    }
    public static class Builder {
        // Обязательные параметры
        private String cpu;

        // Необязательные параметры со значениями по умолчанию
        private int ram = 8;
        private int storage = 256;
        private boolean hasGraphicsCard = false;

        public Builder(String cpu) {
            this.cpu = cpu;
        }

        public Builder ram(int ram) {
            this.ram = ram;
            return this;
        }

        public Builder storage(int storage) {
            this.storage = storage;
            return this;
        }

        public Builder hasGraphicsCard(boolean hasGraphicsCard) {
            this.hasGraphicsCard = hasGraphicsCard;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}

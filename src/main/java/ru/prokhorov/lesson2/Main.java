package ru.prokhorov.lesson2;

import static ru.prokhorov.lesson2.BinarySearch.binarySearch;

/**
 * @author Evgeniy_Prohorov
 */
public class Main {
    public static void main(String[] args) {
        int[] data = {1, 3, 5, 7, 9, 11, 13};

        // Тест 1: элемент в начале массива
        System.out.println("Поиск 1: " + binarySearch(data, 1) + " (ожидается: 0)");

        // Тест 2: элемент в конце массива
        System.out.println("Поиск 13: " + binarySearch(data, 13) + " (ожидается: 6)");

        // Тест 3: элемент отсутствует
        System.out.println("Поиск 8: " + binarySearch(data, 8) + " (ожидается: -1)");

        // Дополнительные тесты
        System.out.println("Поиск 7: " + binarySearch(data, 7) + " (ожидается: 3)");
        System.out.println("Поиск 0: " + binarySearch(data, 0) + " (ожидается: -1)");
        System.out.println("Поиск 15: " + binarySearch(data, 15) + " (ожидается: -1)");
    }
}

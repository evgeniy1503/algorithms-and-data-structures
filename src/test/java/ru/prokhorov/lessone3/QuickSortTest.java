package ru.prokhorov.lessone3;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Evgeniy_Prohorov
 */
class QuickSortTest {

    @Test
    public void testEmptyArray() {
        int[] array = {};
        QuickSort.sort(array);
        assertArrayEquals(new int[]{}, array, "Пустой массив должен остаться пустым");
    }

    @Test
    public void testSingleElement() {
        int[] array = {5};
        QuickSort.sort(array);
        assertArrayEquals(new int[]{5}, array, "Массив из одного элемента должен остаться неизменным");
    }

    @Test
    public void testSortedArray() {
        int[] array = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        QuickSort.sort(array);
        assertArrayEquals(expected, array, "Уже отсортированный массив должен остаться отсортированным");
    }

    @Test
    public void testReverseSortedArray() {
        int[] array = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};
        QuickSort.sort(array);
        assertArrayEquals(expected, array, "Обратно отсортированный массив должен быть отсортирован");
    }

    @Test
    public void testUnsortedArray() {
        int[] array = {64, 34, 25, 12, 22, 11, 90};
        int[] expected = {11, 12, 22, 25, 34, 64, 90};
        QuickSort.sort(array);
        assertArrayEquals(expected, array, "Неотсортированный массив должен быть правильно отсортирован");
    }

    @Test
    public void testArrayWithDuplicates() {
        int[] array = {4, 2, 4, 1, 2, 3};
        int[] expected = {1, 2, 2, 3, 4, 4};
        QuickSort.sort(array);
        assertArrayEquals(expected, array, "Массив с дубликатами должен быть правильно отсортирован");
    }

    @Test
    public void testArrayWithNegativeNumbers() {
        int[] array = {-5, 3, -2, 0, 7, -1};
        int[] expected = {-5, -2, -1, 0, 3, 7};
        QuickSort.sort(array);
        assertArrayEquals(expected, array, "Массив с отрицательными числами должен быть правильно отсортирован");
    }

    @Test
    public void testAllEqualElements() {
        int[] array = {7, 7, 7, 7, 7};
        int[] expected = {7, 7, 7, 7, 7};
        QuickSort.sort(array);
        assertArrayEquals(expected, array, "Массив с одинаковыми элементами должен остаться неизменным");
    }

    @Test
    public void testLargeRandomArray() {
        int size = 1000;
        int[] array = new int[size];
        Random random = new Random(42); // Фиксируем seed для воспроизводимости

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(10000);
        }

        int[] expected = array.clone();
        Arrays.sort(expected); // Используем стандартную сортировку для проверки

        QuickSort.sort(array);

        assertArrayEquals(expected, array, "Большой случайный массив должен быть правильно отсортирован");
    }

    @Test
    public void testNullArray() {
        int[] array = null;
        // Не должно выбрасывать исключение
        assertDoesNotThrow(() -> QuickSort.sort(array),
                "Метод должен корректно обрабатывать null массив");
    }

    @Test
    public void testPerformanceWithLargeArray() {
        int size = 10000;
        int[] array = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100000);
        }

        long startTime = System.currentTimeMillis();
        QuickSort.sort(array);
        long endTime = System.currentTimeMillis();

        // Проверяем что массив отсортирован
        for (int i = 0; i < size - 1; i++) {
            assertTrue(array[i] <= array[i + 1],
                    "Массив должен быть отсортирован по возрастанию");
        }

        long duration = endTime - startTime;
        System.out.println("Сортировка массива из " + size + " элементов заняла: " + duration + " мс");

        // Проверяем что сортировка выполняется за разумное время (менее 1 секунды)
        assertTrue(duration < 1000, "Сортировка должна выполняться за разумное время");
    }

}
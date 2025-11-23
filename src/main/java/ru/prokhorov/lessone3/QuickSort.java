package ru.prokhorov.lessone3;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Быстрая сортировка
 *
 * @author Evgeniy_Prohorov
 */
public class QuickSort {
    public static void sort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        // Создаем стек для хранения границ подмассивов, которые нужно отсортировать
        Deque<Integer> stack = new ArrayDeque<>();

        // Помещаем начальные границы всего массива в стек
        stack.push(0); // левая граница
        stack.push(array.length - 1); // правая граница

        // Пока стек не пуст, обрабатываем подмассивы
        while (!stack.isEmpty()) {
            // Извлекаем правую и левую границы текущего подмассива
            int right = stack.pop();
            int left = stack.pop();

            // Если подмассив содержит менее 2 элементов, пропускаем его
            if (left >= right) {
                continue;
            }

            // Выбираем опорный элемент (в данной реализации - последний элемент)
            int pivotIndex = partition(array, left, right);

            // Помещаем в стек левую часть (элементы меньше опорного)
            if (left < pivotIndex - 1) {
                stack.push(left);
                stack.push(pivotIndex - 1);
            }

            // Помещаем в стек правую часть (элементы больше опорного)
            if (pivotIndex + 1 < right) {
                stack.push(pivotIndex + 1);
                stack.push(right);
            }
        }
    }

    /**
     * Функция разделения массива относительно опорного элемента
     * @param array массив для сортировки
     * @param left левая граница подмассива
     * @param right правая граница подмассива
     * @return индекс опорного элемента после разделения
     */
    private static int partition(int[] array, int left, int right) {
        // Выбираем опорный элемент (последний элемент подмассива)
        int pivot = array[right];

        // Индекс, указывающий на позицию, где будут находиться элементы меньше опорного
        int i = left - 1;

        // Проходим по всем элементам подмассива (кроме опорного)
        for (int j = left; j < right; j++) {
            // Если текущий элемент меньше или равен опорному
            if (array[j] <= pivot) {
                i++; // увеличиваем индекс для меньших элементов
                swap(array, i, j); // перемещаем меньший элемент влево
            }
        }

        // Помещаем опорный элемент на правильную позицию
        swap(array, i + 1, right);

        // Возвращаем индекс опорного элемента
        return i + 1;
    }

    /**
     * Вспомогательная функция для обмена элементов массива
     */
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

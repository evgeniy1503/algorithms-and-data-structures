package ru.prokhorov.lesson2;

/**
 * Класс бинарного поиска
 *
 * @author Evgeniy_Prohorov
 */
public class BinarySearch {

    /**
     * Получение индекса искомого значения, если элемент не найден возвращаем -1
     *
     * @param array массив
     * @param target искомый элемент
     * @return индекс элемента в массиве
     */
    public static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}

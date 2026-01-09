package ru.prokhorov.task_6_2;

import java.util.*;

/**
 * @author Evgeniy_Prohorov
 */
public class TopologicalSort {
    /**
     * Выполняет топологическую сортировку ориентированного графа
     * @param adjacencyMatrix матрица смежности графа (n x n)
     * @return список вершин в топологическом порядке или пустой список, если граф содержит цикл
     */
    public static List<Integer> topologicalSort(int[][] adjacencyMatrix) {
        if (adjacencyMatrix == null || adjacencyMatrix.length == 0) {
            return new ArrayList<>();
        }

        int n = adjacencyMatrix.length;

        // Проверка корректности матрицы
        if (adjacencyMatrix[0].length != n) {
            throw new IllegalArgumentException("Матрица смежности должна быть квадратной");
        }

        // 1. Вычисляем полустепень захода для каждой вершины
        int[] inDegree = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (adjacencyMatrix[i][j] != 0) {
                    inDegree[j]++;
                }
            }
        }

        // 2. Добавляем в очередь вершины с нулевой полустепенью захода
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // 3. Обрабатываем очередь
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            result.add(vertex);

            // Уменьшаем полустепень захода для всех смежных вершин
            for (int j = 0; j < n; j++) {
                if (adjacencyMatrix[vertex][j] != 0) {
                    inDegree[j]--;
                    if (inDegree[j] == 0) {
                        queue.offer(j);
                    }
                }
            }
        }

        // 4. Проверяем, содержит ли граф цикл
        if (result.size() != n) {
            return new ArrayList<>(); // Граф содержит цикл
        }

        return result;
    }
}

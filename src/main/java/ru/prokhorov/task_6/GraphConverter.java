package ru.prokhorov.task_6;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Evgeniy_Prohorov
 */
public class GraphConverter {

    /**
     * Преобразует матрицу смежности в список ребер
     * @param adjacencyMatrix квадратная матрица смежности
     * @return список ребер, где каждое ребро представлено массивом [from, to, weight]
     *         для невзвешенных графов weight = 1
     * @throws IllegalArgumentException если матрица не квадратная или null
     */
    public static List<int[]> adjacencyMatrixToEdgeList(int[][] adjacencyMatrix) {
        if (adjacencyMatrix == null) {
            throw new IllegalArgumentException("Матрица смежности не может быть null");
        }

        int n = adjacencyMatrix.length;

        // Проверяем, что матрица квадратная
        for (int i = 0; i < n; i++) {
            if (adjacencyMatrix[i] == null || adjacencyMatrix[i].length != n) {
                throw new IllegalArgumentException("Матрица смежности должна быть квадратной");
            }
        }

        List<int[]> edgeList = new ArrayList<>();

        // Для неориентированного графа рассматриваем только верхний треугольник матрицы
        // (включая диагональ для петель)
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (adjacencyMatrix[i][j] != 0) {
                    edgeList.add(new int[]{i, j, adjacencyMatrix[i][j]});
                }
            }
        }

        return edgeList;
    }

    /**
     * Преобразует матрицу смежности в список ребер для ориентированного графа
     * @param adjacencyMatrix квадратная матрица смежности
     * @return список ребер, где каждое ребро представлено массивом [from, to, weight]
     * @throws IllegalArgumentException если матрица не квадратная или null
     */
    public static List<int[]> adjacencyMatrixToEdgeListDirected(int[][] adjacencyMatrix) {
        if (adjacencyMatrix == null) {
            throw new IllegalArgumentException("Матрица смежности не может быть null");
        }

        int n = adjacencyMatrix.length;

        // Проверяем, что матрица квадратная
        for (int i = 0; i < n; i++) {
            if (adjacencyMatrix[i] == null || adjacencyMatrix[i].length != n) {
                throw new IllegalArgumentException("Матрица смежности должна быть квадратной");
            }
        }

        List<int[]> edgeList = new ArrayList<>();

        // Для ориентированного графа рассматриваем всю матрицу
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (adjacencyMatrix[i][j] != 0) {
                    edgeList.add(new int[]{i, j, adjacencyMatrix[i][j]});
                }
            }
        }

        return edgeList;
    }

    /**
     * Вспомогательный метод для красивого вывода списка ребер
     */
    public static void printEdgeList(List<int[]> edgeList) {
        System.out.println("Список ребер:");
        System.out.println("От -> До : Вес");
        for (int[] edge : edgeList) {
            System.out.printf("%2d -> %2d : %d%n", edge[0], edge[1], edge[2]);
        }
    }
}


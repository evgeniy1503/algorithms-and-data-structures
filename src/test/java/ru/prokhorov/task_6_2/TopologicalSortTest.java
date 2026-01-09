package ru.prokhorov.task_6_2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Evgeniy_Prohorov
 */
class TopologicalSortTest {

    @Test
    @DisplayName("Пустой граф")
    void testEmptyGraph() {
        int[][] graph = {};
        List<Integer> result = TopologicalSort.topologicalSort(graph);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Одинокая вершина")
    void testSingleVertex() {
        int[][] graph = {{0}};
        List<Integer> result = TopologicalSort.topologicalSort(graph);
        assertEquals(Arrays.asList(0), result);
    }

    @Test
    @DisplayName("Несколько независимых вершин")
    void testIndependentVertices() {
        int[][] graph = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        List<Integer> result = TopologicalSort.topologicalSort(graph);
        assertEquals(3, result.size());
        // Любой порядок допустим
        assertTrue(result.contains(0));
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
    }

    @Test
    @DisplayName("Линейная цепочка")
    void testLinearChain() {
        // 0 → 1 → 2
        int[][] graph = {
                {0, 1, 0},
                {0, 0, 1},
                {0, 0, 0}
        };
        List<Integer> result = TopologicalSort.topologicalSort(graph);
        assertEquals(Arrays.asList(0, 1, 2), result);
    }

    @Test
    @DisplayName("Вилкообразный граф")
    void testForkGraph() {
        //   0
        //  / \
        // 1   2
        //  \ /
        //   3
        int[][] graph = {
                {0, 1, 1, 0},
                {0, 0, 0, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 0}
        };
        List<Integer> result = TopologicalSort.topologicalSort(graph);
        assertEquals(4, result.size());
        assertEquals(0, result.get(0)); // 0 должен быть первым
        assertEquals(3, result.get(3)); // 3 должен быть последним
        // 1 и 2 могут быть в любом порядке
        assertTrue(result.indexOf(1) > 0 && result.indexOf(1) < 3);
        assertTrue(result.indexOf(2) > 0 && result.indexOf(2) < 3);
    }

    @Test
    @DisplayName("Граф с циклом")
    void testGraphWithCycle() {
        // 0 → 1 → 2 → 0 (цикл)
        int[][] graph = {
                {0, 1, 0},
                {0, 0, 1},
                {1, 0, 0}
        };
        List<Integer> result = TopologicalSort.topologicalSort(graph);
        assertTrue(result.isEmpty()); // Должен вернуть пустой список при наличии цикла
    }

    @Test
    @DisplayName("Сложный граф без циклов")
    void testComplexAcyclicGraph() {
        // 0 → 1 → 3
        //   ↘ 2 ↗
        int[][] graph = {
                {0, 1, 1, 0},
                {0, 0, 0, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 0}
        };
        List<Integer> result = TopologicalSort.topologicalSort(graph);
        assertEquals(4, result.size());

        // Проверяем зависимости
        assertTrue(result.indexOf(0) < result.indexOf(1));
        assertTrue(result.indexOf(0) < result.indexOf(2));
        assertTrue(result.indexOf(1) < result.indexOf(3));
        assertTrue(result.indexOf(2) < result.indexOf(3));
    }

    @Test
    @DisplayName("Граф с весами (игнорируемыми)")
    void testWeightedGraph() {
        // 0 → 1 → 2 с весами
        int[][] graph = {
                {0, 5, 0},
                {0, 0, 3},
                {0, 0, 0}
        };
        List<Integer> result = TopologicalSort.topologicalSort(graph);
        assertEquals(Arrays.asList(0, 1, 2), result);
    }

}
package ru.prokhorov.task_6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Evgeniy_Prohorov
 */
class GraphConverterTest {

    @Nested
    @DisplayName("Тесты для неориентированных графов")
    class UndirectedGraphTests {

        @Test
        @DisplayName("Неориентированный невзвешенный граф")
        void testUndirectedUnweightedGraph() {
            // Граф с 3 вершинами: 0-1, 1-2
            int[][] adjacencyMatrix = {
                    {0, 1, 0},
                    {1, 0, 1},
                    {0, 1, 0}
            };

            List<int[]> result = GraphConverter.adjacencyMatrixToEdgeList(adjacencyMatrix);

            assertEquals(2, result.size(), "Должно быть 2 ребра");

            // Проверяем первое ребро 0-1
            assertArrayEquals(new int[]{0, 1, 1}, result.get(0), "Первое ребро должно быть 0-1 с весом 1");

            // Проверяем второе ребро 1-2
            assertArrayEquals(new int[]{1, 2, 1}, result.get(1), "Второе ребро должно быть 1-2 с весом 1");
        }

        @Test
        @DisplayName("Неориентированный взвешенный граф")
        void testUndirectedWeightedGraph() {
            // Взвешенный граф с 3 вершинами
            int[][] adjacencyMatrix = {
                    {0, 5, 0},
                    {5, 0, 3},
                    {0, 3, 0}
            };

            List<int[]> result = GraphConverter.adjacencyMatrixToEdgeList(adjacencyMatrix);

            assertEquals(2, result.size(), "Должно быть 2 ребра");
            assertArrayEquals(new int[]{0, 1, 5}, result.get(0), "Ребро 0-1 должно иметь вес 5");
            assertArrayEquals(new int[]{1, 2, 3}, result.get(1), "Ребро 1-2 должно иметь вес 3");
        }

        @Test
        @DisplayName("Граф с петлями")
        void testGraphWithLoops() {
            // Граф с петлями в вершинах 0 и 2
            int[][] adjacencyMatrix = {
                    {1, 1, 0},
                    {1, 0, 1},
                    {0, 1, 2}
            };

            List<int[]> result = GraphConverter.adjacencyMatrixToEdgeList(adjacencyMatrix);

            assertEquals(4, result.size(), "Должно быть 4 ребра (включая петли)");

            // Проверяем петли
            boolean hasLoop0 = false;
            boolean hasLoop2 = false;

            for (int[] edge : result) {
                if (edge[0] == 0 && edge[1] == 0) {
                    hasLoop0 = true;
                    assertEquals(1, edge[2], "Петля в вершине 0 должна иметь вес 1");
                }
                if (edge[0] == 2 && edge[1] == 2) {
                    hasLoop2 = true;
                    assertEquals(2, edge[2], "Петля в вершине 2 должна иметь вес 2");
                }
            }

            assertTrue(hasLoop0, "Должна быть петля в вершине 0");
            assertTrue(hasLoop2, "Должна быть петля в вершине 2");
        }

        @Test
        @DisplayName("Полный граф K3")
        void testCompleteGraphK3() {
            // Полный граф с 3 вершинами
            int[][] adjacencyMatrix = {
                    {0, 1, 1},
                    {1, 0, 1},
                    {1, 1, 0}
            };

            List<int[]> result = GraphConverter.adjacencyMatrixToEdgeList(adjacencyMatrix);

            assertEquals(3, result.size(), "В полном графе K3 должно быть 3 ребра");

            // Проверяем наличие всех ребер
            boolean hasEdge01 = false;
            boolean hasEdge02 = false;
            boolean hasEdge12 = false;

            for (int[] edge : result) {
                if ((edge[0] == 0 && edge[1] == 1) || (edge[0] == 1 && edge[1] == 0)) {
                    hasEdge01 = true;
                }
                if ((edge[0] == 0 && edge[1] == 2) || (edge[0] == 2 && edge[1] == 0)) {
                    hasEdge02 = true;
                }
                if ((edge[0] == 1 && edge[1] == 2) || (edge[0] == 2 && edge[1] == 1)) {
                    hasEdge12 = true;
                }
            }

            assertTrue(hasEdge01, "Должно быть ребро 0-1");
            assertTrue(hasEdge02, "Должно быть ребро 0-2");
            assertTrue(hasEdge12, "Должно быть ребро 1-2");
        }

        @Test
        @DisplayName("Граф без ребер")
        void testEmptyGraph() {
            // Граф с 3 вершинами без ребер
            int[][] adjacencyMatrix = {
                    {0, 0, 0},
                    {0, 0, 0},
                    {0, 0, 0}
            };

            List<int[]> result = GraphConverter.adjacencyMatrixToEdgeList(adjacencyMatrix);

            assertTrue(result.isEmpty(), "Список ребер должен быть пустым");
        }

        @Test
        @DisplayName("Граф с одной вершиной")
        void testSingleVertexGraph() {
            // Граф с одной вершиной и петлей
            int[][] adjacencyMatrix = {{2}};

            List<int[]> result = GraphConverter.adjacencyMatrixToEdgeList(adjacencyMatrix);

            assertEquals(1, result.size(), "Должна быть одна петля");
            assertArrayEquals(new int[]{0, 0, 2}, result.get(0), "Петля должна иметь вес 2");
        }
    }

    @Nested
    @DisplayName("Тесты обработки ошибок")
    class ErrorHandlingTests {

        @Test
        @DisplayName("Null матрица")
        void testNullMatrix() {
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> GraphConverter.adjacencyMatrixToEdgeList(null),
                    "Должно быть выброшено исключение для null матрицы"
            );

            assertTrue(exception.getMessage().contains("не может быть null"),
                    "Сообщение об ошибке должно указывать на null");
        }

        @Test
        @DisplayName("Неквадратная матрица")
        void testNonSquareMatrix() {
            int[][] nonSquareMatrix = {
                    {0, 1, 2},
                    {1, 0, 1}  // Не хватает третьего элемента
            };

            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> GraphConverter.adjacencyMatrixToEdgeList(nonSquareMatrix),
                    "Должно быть выброшено исключение для неквадратной матрицы"
            );

            assertTrue(exception.getMessage().contains("квадратной"),
                    "Сообщение об ошибке должно указывать на неквадратность матрицы");
        }
    }

}
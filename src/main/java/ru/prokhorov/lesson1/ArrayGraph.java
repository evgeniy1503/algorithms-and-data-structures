package ru.prokhorov.lesson1;

import java.util.List;
import java.util.ArrayList;

/**
 * Графа реализация через массив (матрица смежности).
 *
 * @author Evgeniy_Prokhorov
 */
public class ArrayGraph implements Graph {
    private int[][] adjacencyMatrix;
    private int vertexCount;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayGraph() {
        this.adjacencyMatrix = new int[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertexCount = 0;
    }

    public ArrayGraph(int initialCapacity) {
        this.adjacencyMatrix = new int[initialCapacity][initialCapacity];
        this.vertexCount = 0;
    }

    @Override
    public void addVertex() {
        if (vertexCount >= adjacencyMatrix.length) {
            resizeMatrix();
        }
        vertexCount++;
    }

    private void resizeMatrix() {
        int newSize = adjacencyMatrix.length * 2;
        int[][] newMatrix = new int[newSize][newSize];

        for (int i = 0; i < vertexCount; i++) {
            System.arraycopy(adjacencyMatrix[i], 0, newMatrix[i], 0, vertexCount);
        }

        adjacencyMatrix = newMatrix;
    }

    @Override
    public void addEdge(int from, int to) {
        if (from < 0 || from >= vertexCount || to < 0 || to >= vertexCount) {
            throw new IllegalArgumentException("Неверные индексы вершин");
        }
        adjacencyMatrix[from][to] = 1;
        adjacencyMatrix[to][from] = 1; // Для неориентированного графа
    }

    @Override
    public void removeEdge(int from, int to) {
        if (from < 0 || from >= vertexCount || to < 0 || to >= vertexCount) {
            throw new IllegalArgumentException("Неверные индексы вершин");
        }
        adjacencyMatrix[from][to] = 0;
        adjacencyMatrix[to][from] = 0;
    }

    @Override
    public boolean hasEdge(int from, int to) {
        if (from < 0 || from >= vertexCount || to < 0 || to >= vertexCount) {
            return false;
        }
        return adjacencyMatrix[from][to] == 1;
    }

    @Override
    public List<Integer> getAdjacentVertices(int vertex) {
        if (vertex < 0 || vertex >= vertexCount) {
            throw new IllegalArgumentException("Неверный индекс вершины");
        }

        List<Integer> adjacent = new ArrayList<>();
        for (int i = 0; i < vertexCount; i++) {
            if (adjacencyMatrix[vertex][i] == 1) {
                adjacent.add(i);
            }
        }
        return adjacent;
    }

    @Override
    public void printGraph() {
        System.out.println("Матрица смежности:");
        System.out.print("  ");
        for (int i = 0; i < vertexCount; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < vertexCount; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < vertexCount; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public int getVertexCount() {
        return vertexCount;
    }
}
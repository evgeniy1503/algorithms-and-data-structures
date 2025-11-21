package ru.prokhorov.lesson1;

import java.util.List;

/**
 * Интерфейс Графа.
 *
 * @author Evgeniy_Prokhorov
 */
public interface Graph {

    /**
     * Добавить вершину
     */
    void addVertex();

    /**
     * Добавление ребра
     *
     * @param from от
     * @param to к
     */
    void addEdge(int from, int to);

    /**
     * Удалить ребро
     *
     * @param from от
     * @param to к
     */
    void removeEdge(int from, int to);

    /**
     * Проверить наличие ребра
     *
     * @param from от
     * @param to к
     * @return есть/ отсутствует
     */
    boolean hasEdge(int from, int to);

    /**
     * Получить смежные вершины
     *
     * @param vertex вершина
     * @return список смежных вершин
     */
    List<Integer> getAdjacentVertices(int vertex);

    /**
     * Вывести граф
     */
    public void printGraph();

    /**
     * Получить количество вершин
     *
     * @return количество вершин
     */
    int getVertexCount();
}

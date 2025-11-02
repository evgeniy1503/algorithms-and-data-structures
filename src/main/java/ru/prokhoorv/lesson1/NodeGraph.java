package ru.prokhoorv.lesson1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Графа реализация через узлы (список смежности).
 *
 * @author Evgeniy_Prokhorov
 */
public class NodeGraph implements Graph {

    private Map<Integer, GraphNode> nodes;
    private int vertexCount;

    public NodeGraph() {
        this.nodes = new HashMap<>();
        this.vertexCount = 0;
    }

    @Override
    public void addVertex() {
        GraphNode newNode = new GraphNode(vertexCount);
        nodes.put(vertexCount, newNode);
        vertexCount++;
    }

    @Override
    public void addEdge(int from, int to) {
        if (from < 0 || from >= vertexCount || to < 0 || to >= vertexCount) {
            throw new IllegalArgumentException("Неверные индексы вершин");
        }

        GraphNode fromNode = nodes.get(from);
        GraphNode toNode = nodes.get(to);

        fromNode.addNeighbor(toNode);
        toNode.addNeighbor(fromNode); // Для неориентированного графа
    }

    @Override
    public void removeEdge(int from, int to) {
        if (from < 0 || from >= vertexCount || to < 0 || to >= vertexCount) {
            throw new IllegalArgumentException("Неверные индексы вершин");
        }

        GraphNode fromNode = nodes.get(from);
        GraphNode toNode = nodes.get(to);

        fromNode.removeNeighbor(toNode);
        toNode.removeNeighbor(fromNode);
    }

    @Override
    public boolean hasEdge(int from, int to) {
        if (from < 0 || from >= vertexCount || to < 0 || to >= vertexCount) {
            return false;
        }

        GraphNode fromNode = nodes.get(from);
        GraphNode toNode = nodes.get(to);

        return fromNode.hasNeighbor(toNode);
    }

    @Override
    public List<Integer> getAdjacentVertices(int vertex) {
        if (vertex < 0 || vertex >= vertexCount) {
            throw new IllegalArgumentException("Неверный индекс вершины");
        }

        GraphNode node = nodes.get(vertex);
        List<Integer> adjacent = new ArrayList<>();

        for (GraphNode neighbor : node.neighbors) {
            adjacent.add(neighbor.id);
        }

        return adjacent;
    }

    @Override
    public void printGraph() {
        System.out.println("Список смежности:");
        for (int i = 0; i < vertexCount; i++) {
            GraphNode node = nodes.get(i);
            System.out.print("Вершина " + i + ": ");
            for (GraphNode neighbor : node.neighbors) {
                System.out.print(neighbor.id + " ");
            }
            System.out.println();
        }
    }

    @Override
    public int getVertexCount() {
        return vertexCount;
    }

    /**
     * Класс для представления узла графа
     */
    private static class GraphNode {
        int id;
        List<GraphNode> neighbors;

        public GraphNode(int id) {
            this.id = id;
            this.neighbors = new ArrayList<>();
        }

        public void addNeighbor(GraphNode neighbor) {
            if (!neighbors.contains(neighbor)) {
                neighbors.add(neighbor);
            }
        }

        public void removeNeighbor(GraphNode neighbor) {
            neighbors.remove(neighbor);
        }

        public boolean hasNeighbor(GraphNode neighbor) {
            return neighbors.contains(neighbor);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            GraphNode graphNode = (GraphNode) obj;
            return id == graphNode.id;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(id);
        }
    }
}

package ru.arlen;

import java.util.Set;

public class DirectedGraph<T extends Comparable<T>> extends Graph<T> {
    @Override
    public void addEdge(T inVertex, T outVertex) {
        if (!hasVertex(inVertex))
            addVertex(inVertex);
        if (!hasVertex(outVertex))
            addVertex(outVertex);
        Set<T> edges = vertices.get(inVertex);
        edges.add(outVertex);
    }
}
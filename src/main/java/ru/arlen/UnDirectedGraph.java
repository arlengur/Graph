package ru.arlen;

import java.util.Set;

public class UnDirectedGraph<T extends Comparable<T>> extends Graph<T> {
    @Override
    public void addEdge(T inVertex, T outVertex) {
        if (!hasVertex(inVertex))
            addVertex(inVertex);
        if (!hasVertex(outVertex))
            addVertex(outVertex);
        Set<T> edges1 = vertices.get(inVertex);
        Set<T> edges2 = vertices.get(outVertex);
        edges1.add(outVertex);
        edges2.add(inVertex);
    }
}
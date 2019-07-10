package ru.arlen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public abstract class Graph<T extends Comparable<T>> {
    protected Map<T, Set<T>> vertices = null;

    protected Graph() {
        vertices = new HashMap<T, Set<T>>();
    }

    public void addVertex(T vertex) {
        if (!hasVertex(vertex)) {
            vertices.put(vertex, new TreeSet<>());
        }
    }

    public abstract void addEdge(T inVertex, T outVertex);

    protected boolean hasVertex(T vertex) {
        return vertices.containsKey(vertex);
    }

    public Map<T, Set<T>> getGrah() {
        return vertices;
    }

    public List<List<T>> getPath(T startVertex, T endVertex) {
        LinkedList<T> visited = new LinkedList<>();
        List<List<T>> paths = new ArrayList<>();
        visited.add(startVertex);
        dfs1(visited, endVertex, paths);
        return paths;
    }

    @SuppressWarnings("unchecked")
    public void dfs1(LinkedList<T> visited, T endVertex, List<List<T>> paths) {
        Set<T> adjacent = vertices.get(visited.getLast());
        for (T v : adjacent) {
            if (visited.contains(v))
                continue;
            if (v.equals(endVertex)) {
                visited.add(v);
                paths.add((List<T>) visited.clone());
                visited.removeLast();
                break;
            }
        }
        for (T v : adjacent) {
            if (visited.contains(v) || v.equals(endVertex))
                continue;
            visited.addLast(v);
            dfs1(visited, endVertex, paths);
            visited.removeLast();

        }
    }

    @Override
    public String toString() {
        return vertices.keySet().stream().map(key -> key + "->" + vertices.get(key))
                .collect(Collectors.joining(", ", "{", "}"));
    }
}
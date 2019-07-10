package ru.arlen;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class GraphTest {
    private <T> Set<T> asSet(T... vals) {
        return Stream.of(vals).collect(Collectors.toSet());
    }

    @Test
    public void testUnDirectedGraph() {
        Graph<Integer> graph = new UnDirectedGraph<>();
        graph.addEdge(1, 3);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);

        assertThat(asSet(3, 4), is(graph.getGrah().get(1)));
        assertThat(asSet(3), is(graph.getGrah().get(2)));
        assertThat(asSet(1, 2, 5), is(graph.getGrah().get(3)));
        assertThat(asSet(1, 5), is(graph.getGrah().get(4)));
        assertThat(asSet(3, 4), is(graph.getGrah().get(5)));
    }

    @Test
    public void testDirectedGraph() {
        Graph<Integer> graph = new DirectedGraph<>();
        graph.addEdge(1, 3);
        graph.addEdge(3, 2);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);

        assertThat(asSet(3), is(graph.getGrah().get(1)));
        assertThat(asSet(), is(graph.getGrah().get(2)));
        assertThat(asSet(2, 4), is(graph.getGrah().get(3)));
        assertThat(asSet(5), is(graph.getGrah().get(4)));
        assertThat(asSet(), is(graph.getGrah().get(5)));
    }

    @Test
    public void testUnDirectedGraphPath() {
        Graph<Integer> graph = new UnDirectedGraph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);

        assertThat(asList(asList(1, 3, 5), asList(1, 4, 5)), is(graph.getPath(1, 5)));
        assertThat(asList(asList(1, 3, 2), asList(1, 4, 5, 3, 2)), is(graph.getPath(1, 2)));
    }

    @Test
    public void testDirectedGraphPath() {
        Graph<Integer> graph = new DirectedGraph<>();
        graph.addEdge(1, 3);
        graph.addEdge(3, 2);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);

        assertThat(asList(asList(1, 3, 4, 5)), is(graph.getPath(1, 5)));
        assertThat(asList(asList(1, 3, 2)), is(graph.getPath(1, 2)));;
    }

    @Test
    public void testUnDirectedGraphToString() {
        Graph<Integer> graph = new UnDirectedGraph<>();
        graph.addEdge(1, 3);
        assertThat("{1->[3], 3->[1]}", is(graph.toString()));
    }
}

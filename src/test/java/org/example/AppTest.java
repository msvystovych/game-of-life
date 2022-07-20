package org.example;

import org.example.exception.NodeNotFoundException;
import org.example.model.Node;
import org.junit.Test;

import java.util.List;

import static org.example.util.Util.printPath;
import static org.junit.Assert.assertEquals;


public class AppTest {
    @Test(expected = NodeNotFoundException.class)
    public void noStartPoint() {
        char[][] data = {
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'E', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', '0', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}
        };
        App.findShortestPath(data);
    }

    @Test(expected = NodeNotFoundException.class)
    public void noEndPoint() {
        char[][] data = {
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'S', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}
        };
        App.findShortestPath(data);
    }

    @Test
    public void noObstacles() {
        char[][] data = {
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'E', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'S', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}
        };
        List<Node> shortestPath = App.findShortestPath(data);
        assertEquals(new Node(4, 6, 'S'), shortestPath.get(0));
        assertEquals(new Node(3, 6, 'O'), shortestPath.get(1));
        assertEquals(new Node(2, 6, 'E'), shortestPath.get(2));
    }

    @Test
    public void withObstacles1() {
        char[][] data = {
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', '#', 'E', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', '#', '#', '#', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'S', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}
        };
        List<Node> result = App.findShortestPath(data);
        printPath(App.findShortestPath(data), data);
        assertEquals(new Node(4, 6), result.get(0));
        assertEquals(new Node(3, 7), result.get(1));
        assertEquals(new Node(2, 6), result.get(2));
    }

    @Test
    public void withObstacles2() {
        char[][] data = {
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', '#', 'E', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', '#', '#', '#', '#', '#', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'S', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}
        };
        List<Node> result = App.findShortestPath(data);
        printPath(App.findShortestPath(data), data);
        assertEquals(new Node(4, 6), result.get(0));
        assertEquals(new Node(4, 7), result.get(1));
        assertEquals(new Node(4, 8), result.get(2));
        assertEquals(new Node(3, 9), result.get(3));
        assertEquals(new Node(2, 8), result.get(4));
        assertEquals(new Node(2, 7), result.get(5));
        assertEquals(new Node(2, 6), result.get(6));
    }

    @Test
    public void pathIsEmpty() {
        char[][] data = {
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'E', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'S', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', '0', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}
        };
        List<Node> result = App.findShortestPath(data);
        printPath(App.findShortestPath(data), data);
        assertEquals(new Node(3, 6), result.get(0));
        assertEquals(new Node(2, 6), result.get(1));
    }

    @Test
    public void mustDoDiagonalMove() {
        char[][] data = {
                {'O', 'O', 'O', 'E', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', '0', 'S', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}
        };
        List<Node> result = App.findShortestPath(data);
        printPath(App.findShortestPath(data), data);
        assertEquals(new Node(4, 7), result.get(0));
        assertEquals(new Node(3, 6), result.get(1));
        assertEquals(new Node(2, 5), result.get(2));
        assertEquals(new Node(1, 4), result.get(3));
        assertEquals(new Node(0, 3), result.get(4));
    }
}

package Ch_12_Graphs.CycleDetectionDirectedGraph;
//Problem: Given a directed graph, find if there is a cycle in Graph. In a single
//traversal, if some node is traversed twice then there is a cycle.
import java.util.*;

class Edge {
    int src, dest, weight;

    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

class Graph {
    int count;
    List<LinkedList<Edge>> Adj;

    Graph(int count) {
        this.count = count;
        Adj = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            Adj.add(new LinkedList<>());
        }
    }

    void addDirectedEdge(int src, int dest, int weight) {
        Edge edge = new Edge(src, dest, weight);
        Adj.get(src).add(edge);
    }

    void print() {
        for (int i = 0; i < count; i++) {
            LinkedList<Edge> list = Adj.get(i);
            System.out.print("Vertex " + i + " is connected to: ");
            for (Edge edge : list) {
                System.out.print(edge.dest + " ");
            }
            System.out.println();
        }
    }
}

public class CycleDetectionDirectedGraph {
    public static boolean isCyclePresentDFS(Graph graph, int index, boolean[] visited, int[] marked) {
        visited[index] = true;
        marked[index] = 1;
        LinkedList<Edge> adl = graph.Adj.get(index);

        for (Edge adn : adl) {
            int dest = adn.dest;
            if (marked[dest] == 1) {
                return true;
            }
            if (!visited[dest]) {
                if (isCyclePresentDFS(graph, dest, visited, marked)) {
                    return true;
                }
            }
        }
        marked[index] = 0;
        return false;
    }

    public static boolean isCyclePresent(Graph graph) {
        int count = graph.count;
        boolean[] visited = new boolean[count];
        int[] marked = new int[count];

        for (int index = 0; index < count; index++) {
            if (!visited[index]) {
                if (isCyclePresentDFS(graph, index, visited, marked)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Graph gph = new Graph(6);
        gph.addDirectedEdge(0, 1, 1);
        gph.addDirectedEdge(0, 2, 1);
        gph.addDirectedEdge(1, 3, 1);
        gph.addDirectedEdge(2, 4, 1);
        gph.addDirectedEdge(3, 5, 1);
        gph.addDirectedEdge(4, 5, 1);
        gph.addDirectedEdge(5, 2, 1);  // Adding more edges to create a cycle
        gph.print();

        boolean hasCycle = isCyclePresent(gph);
        if (hasCycle) {
            System.out.println("The graph contains a cycle.");
        } else {
            System.out.println("The graph does not contain a cycle.");
        }
    }
}


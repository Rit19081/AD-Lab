package Ch_12_Graphs.StronglyConnectedGraph;

import java.util.*;

class Edge {
    int src, dest;

    Edge(int src, int dest) {
        this.src = src;
        this.dest = dest;
    }
}

class Graph {
    int count;
    List<LinkedList<Integer>> Adj;

    Graph(int count) {
        this.count = count;
        Adj = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            Adj.add(new LinkedList<>());
        }
    }

    void addDirectedEdge(int src, int dest) {
        Adj.get(src).add(dest);
    }

    void print() {
        for (int i = 0; i < count; i++) {
            LinkedList<Integer> list = Adj.get(i);
            System.out.print("Vertex " + i + " is connected to: ");
            for (int neighbor : list) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }
}

public class StronglyConnectedGraph {
    public static void dfsUtil(Graph graph, int index, boolean[] visited) {
        visited[index] = true;
        LinkedList<Integer> adl = graph.Adj.get(index);
        for (int neighbor : adl) {
            if (!visited[neighbor]) {
                dfsUtil(graph, neighbor, visited);
            }
        }
    }

    public static Graph transposeGraph(Graph gph) {
        int count = gph.count;
        Graph g = new Graph(count);

        for (int i = 0; i < count; i++) {
            LinkedList<Integer> adl = gph.Adj.get(i);
            for (int dest : adl) {
                g.addDirectedEdge(dest, i); // Reverse the edge direction
            }
        }

        return g;
    }

    public static boolean isStronglyConnected(Graph gph) {
        int count = gph.count;
        boolean[] visited = new boolean[count];

        // Step 1: Perform DFS on the original graph gph
        dfsUtil(gph, 0, visited);

        // Check if all vertices are visited
        for (int i = 0; i < count; i++) {
            if (!visited[i]) {
                return false;
            }
        }

        // Step 2: Get the transpose of the graph
        Graph gReversed = transposeGraph(gph);

        // Reset visited array for the reversed graph
        for (int i = 0; i < count; i++) {
            visited[i] = false;
        }

        // Step 3: Perform DFS on the reversed graph gReversed
        dfsUtil(gReversed, 0, visited);

        // Check if all vertices are visited in the reversed graph
        for (int i = 0; i < count; i++) {
            if (!visited[i]) {
                return false;
            }
        }

        // If both DFS traversals mark all vertices as visited, the graph is strongly connected
        return true;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addDirectedEdge(0, 1);
        graph.addDirectedEdge(1, 2);
        graph.addDirectedEdge(2, 0);
        graph.addDirectedEdge(2, 3);
        graph.addDirectedEdge(3, 4);
        graph.print();

        boolean isConnected = isStronglyConnected(graph);
        if (isConnected) {
            System.out.println("The graph is strongly connected.");
        } else {
            System.out.println("The graph is not strongly connected.");
        }
    }
}

package Ch_12_Graphs.ConnectedUndirectedGraph;
//Test if an undirected graph is connected.
//Problem: Given an undirected graph. Start from any vertex if we can visit all the
//other vertices using DFS or BFS then the graph is connected.
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

    void addEdge(int src, int dest) {
        Adj.get(src).add(dest);
        Adj.get(dest).add(src); // For undirected graph, add both directions
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

public class ConnectedUndirectedGraph {
    public static void dfsUtil(Graph graph, int index, boolean[] visited) {
        visited[index] = true;
        LinkedList<Integer> adl = graph.Adj.get(index);
        for (int neighbor : adl) {
            if (!visited[neighbor]) {
                dfsUtil(graph, neighbor, visited);
            }
        }
    }

    public static boolean isConnectedUndirected(Graph graph) {
        int count = graph.count;
        boolean[] visited = new boolean[count];
        dfsUtil(graph, 0, visited); // Start DFS from vertex 0

        // Check if all vertices are visited
        for (int i = 0; i < count; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.print();

        boolean isConnected = isConnectedUndirected(graph);
        if (isConnected) {
            System.out.println("The graph is connected.");
        } else {
            System.out.println("The graph is not connected.");
        }
    }
}


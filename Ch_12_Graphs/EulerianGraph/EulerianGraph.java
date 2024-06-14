package Ch_12_Graphs.EulerianGraph;

import java.util.*;

class Edge {
    int dest;
    int cost;

    Edge(int dest, int cost) {
        this.dest = dest;
        this.cost = cost;
    }
}

class Graph {
    int count;
    ArrayList<LinkedList<Edge>> Adj;

    Graph(int count) {
        this.count = count;
        Adj = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            Adj.add(new LinkedList<>());
        }
    }

    void addDirectedEdge(int src, int dest, int cost) {
        Adj.get(src).add(new Edge(dest, cost));
    }

    void addUndirectedEdge(int src, int dest, int cost) {
        Adj.get(src).add(new Edge(dest, cost));
        Adj.get(dest).add(new Edge(src, cost));
    }
}

public class EulerianGraph {
    public static void main(String[] args) {
        int count = 5;
        Graph graph = new Graph(count);

        // Example graph 1 (Eulerian)
        graph.addUndirectedEdge(0, 1, 1);
        graph.addUndirectedEdge(1, 2, 1);
        graph.addUndirectedEdge(2, 0, 1);
        graph.addUndirectedEdge(1, 3, 1);
        graph.addUndirectedEdge(3, 4, 1);
        graph.addUndirectedEdge(4, 1, 1);

        System.out.println("Graph 1: " + eulerianType(graph));

        // Example graph 2 (Semi-Eulerian)
        Graph graph2 = new Graph(count);
        graph2.addUndirectedEdge(0, 1, 1);
        graph2.addUndirectedEdge(1, 2, 1);
        graph2.addUndirectedEdge(2, 0, 1);
        graph2.addUndirectedEdge(1, 3, 1);
        graph2.addUndirectedEdge(3, 4, 1);

        System.out.println("Graph 2: " + eulerianType(graph2));

        // Example graph 3 (Not Eulerian)
        Graph graph3 = new Graph(count);
        graph3.addUndirectedEdge(0, 1, 1);
        graph3.addUndirectedEdge(1, 2, 1);
        graph3.addUndirectedEdge(1, 3, 1);
        graph3.addUndirectedEdge(3, 4, 1);

        System.out.println("Graph 3: " + eulerianType(graph3));
    }

    public static int eulerianType(Graph graph) {
        int count = graph.count;
        int odd = 0;
        int[] inDegree = new int[count];
        int[] outDegree = new int[count];

        // Check if all non-zero degree nodes are connected
        if (!isConnected(graph)) {
            System.out.println("Graph is not Eulerian");
            return 0;
        }

        // Count odd degree vertices
        for (int i = 0; i < count; i++) {
            LinkedList<Edge> adl = graph.Adj.get(i);
            for (Edge adn : adl) {
                outDegree[i]++;
                inDegree[adn.dest]++;
            }
        }

        for (int i = 0; i < count; i++) {
            if ((inDegree[i] + outDegree[i]) % 2 != 0) {
                odd++;
            }
        }

        if (odd == 0) {
            System.out.println("Graph is Eulerian");
            return 2;
        } else if (odd == 2) {
            System.out.println("Graph is Semi-Eulerian");
            return 1;
        } else {
            System.out.println("Graph is not Eulerian");
            return 0;
        }
    }

    public static boolean isConnected(Graph graph) {
        boolean[] visited = new boolean[graph.count];

        // Find a vertex with non-zero degree
        int nonZeroDegreeVertex = -1;
        for (int i = 0; i < graph.count; i++) {
            if (graph.Adj.get(i).size() > 0) {
                nonZeroDegreeVertex = i;
                break;
            }
        }

        // If there are no edges in the graph, it is Eulerian
        if (nonZeroDegreeVertex == -1) {
            return true;
        }

        // Start DFS traversal from a vertex with non-zero degree
        dfs(graph, nonZeroDegreeVertex, visited);

        // Check if all vertices with non-zero degree are visited
        for (int i = 0; i < graph.count; i++) {
            if (graph.Adj.get(i).size() > 0 && !visited[i]) {
                return false;
            }
        }

        return true;
    }

    public static void dfs(Graph graph, int vertex, boolean[] visited) {
        visited[vertex] = true;
        for (Edge edge : graph.Adj.get(vertex)) {
            if (!visited[edge.dest]) {
                dfs(graph, edge.dest, visited);
            }
        }
    }
}

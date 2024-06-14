package Ch_12_Graphs.ShortestPathUnweightedGraph;

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
    List<List<Edge>> Adj;

    Graph(int count) {
        this.count = count;
        Adj = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            Adj.add(new ArrayList<>());
        }
    }

    void addDirectedEdge(int src, int dest) {
        Adj.get(src).add(new Edge(src, dest));
    }
}

public class ShortestPathUnweightedGraph {
    public static void shortestPath(Graph gph, int source) {
        int count = gph.count;
        int[] distance = new int[count];
        int[] path = new int[count];
        Arrays.fill(distance, -1);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        distance[source] = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            List<Edge> adl = gph.Adj.get(curr);

            for (Edge edge : adl) {
                int dest = edge.dest;
                if (distance[dest] == -1) {
                    distance[dest] = distance[curr] + 1;
                    path[dest] = curr;
                    queue.add(dest);
                }
            }
        }

        // Printing the shortest path from the source to each vertex
        for (int i = 0; i < count; i++) {
            if (distance[i] != -1) {
                System.out.println(path[i] + " to " + i + " weight " + distance[i]);
            } else {
                System.out.println("No path from " + source + " to " + i);
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addDirectedEdge(0, 1);
        graph.addDirectedEdge(0, 2);
        graph.addDirectedEdge(1, 3);
        graph.addDirectedEdge(2, 4);
        graph.addDirectedEdge(3, 5);
        graph.addDirectedEdge(4, 5);

        shortestPath(graph, 0);
    }
}

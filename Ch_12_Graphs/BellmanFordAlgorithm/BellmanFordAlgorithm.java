package Ch_12_Graphs.BellmanFordAlgorithm;

import java.util.*;
class Edge {
    int src, dest, cost;

    Edge(int src, int dest, int cost) {
        this.src = src;
        this.dest = dest;
        this.cost = cost;
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

    void addDirectedEdge(int src, int dest, int cost) {
        Adj.get(src).add(new Edge(src, dest, cost));
    }
}

public class BellmanFordAlgorithm {

    public static void bellmanFordShortestPath(Graph gph, int source) {
        int count = gph.count;
        int[] distance = new int[count];
        int[] path = new int[count];

        // Initialize distances from source to all vertices as infinite and path as -1
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(path, -1);
        distance[source] = 0;

        // Relax all edges |V| - 1 times.
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count; j++) {
                List<Edge> adl = gph.Adj.get(j);
                for (Edge adn : adl) {
                    if (distance[j] != Integer.MAX_VALUE && distance[adn.dest] > distance[j] + adn.cost) {
                        distance[adn.dest] = distance[j] + adn.cost;
                        path[adn.dest] = j;
                    }
                }
            }
        }

        // Check for negative-weight cycles.
        for (int j = 0; j < count; j++) {
            List<Edge> adl = gph.Adj.get(j);
            for (Edge adn : adl) {
                if (distance[j] != Integer.MAX_VALUE && distance[adn.dest] > distance[j] + adn.cost) {
                    System.out.println("Graph contains negative weight cycle");
                    return;
                }
            }
        }

        // Print the shortest path and distances
        for (int i = 0; i < count; i++) {
            System.out.println("Path to " + i + " with weight " + distance[i] + " through " + path[i]);
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addDirectedEdge(0, 1, -1);
        graph.addDirectedEdge(0, 2, 4);
        graph.addDirectedEdge(1, 2, 3);
        graph.addDirectedEdge(1, 3, 2);
        graph.addDirectedEdge(1, 4, 2);
        graph.addDirectedEdge(3, 2, 5);
        graph.addDirectedEdge(3, 1, 1);
        graph.addDirectedEdge(4, 3, -3);

        bellmanFordShortestPath(graph, 0);
    }
}


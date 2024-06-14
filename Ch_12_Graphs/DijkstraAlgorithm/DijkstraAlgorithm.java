package Ch_12_Graphs.DijkstraAlgorithm;

import java.util.*;

class Edge {
    int src, dest, cost;

    Edge(int src, int dest, int cost) {
        this.src = src;
        this.dest = dest;
        this.cost = cost;
    }

    Edge(int dest, int cost) {
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

public class DijkstraAlgorithm {
    public static void dijkstra(Graph gph, int source) {
        int[] previous = new int[gph.count];
        int[] dist = new int[gph.count];
        boolean[] visited = new boolean[gph.count];

        Arrays.fill(previous, -1);
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        PriorityQueue<Edge> queue = new PriorityQueue<>(100, new EdgeComparator());
        queue.add(new Edge(source, 0));

        while (!queue.isEmpty()) {
            Edge node = queue.poll();
            int current = node.dest;

            if (visited[current]) continue;
            visited[current] = true;

            List<Edge> adl = gph.Adj.get(current);
            for (Edge adn : adl) {
                int dest = adn.dest;
                int alt = adn.cost + dist[current];

                if (dist[dest] > alt) {
                    dist[dest] = alt;
                    previous[dest] = current;
                    queue.add(new Edge(dest, alt));
                }
            }
        }

        // Printing result.
        for (int i = 0; i < gph.count; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("Node id " + i + " prev " + previous[i] + " distance: Unreachable");
            } else {
                System.out.println("Node id " + i + " prev " + previous[i] + " distance: " + dist[i]);
            }
        }
    }

    static class EdgeComparator implements Comparator<Edge> {
        public int compare(Edge x, Edge y) {
            return Integer.compare(x.cost, y.cost);
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addDirectedEdge(0, 1, 4);
        graph.addDirectedEdge(0, 2, 1);
        graph.addDirectedEdge(2, 1, 2);
        graph.addDirectedEdge(1, 3, 1);
        graph.addDirectedEdge(2, 3, 5);
        graph.addDirectedEdge(3, 4, 3);
        graph.addDirectedEdge(4, 5, 1);

        dijkstra(graph, 0);
    }
}

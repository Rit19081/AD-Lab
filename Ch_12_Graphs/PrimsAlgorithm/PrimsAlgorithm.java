package Ch_12_Graphs.PrimsAlgorithm;

import java.util.*;

class Edge {
    int src, dest, cost;

    Edge(int dest, int cost) {
        this.dest = dest;
        this.cost = cost;
    }

    Edge(int src, int dest, int cost) {
        this.src = src;
        this.dest = dest;
        this.cost = cost;
    }
}

class EdgeComparator implements Comparator<Edge> {
    public int compare(Edge edge1, Edge edge2) {
        return edge1.cost - edge2.cost;
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

    void addUndirectedEdge(int src, int dest, int cost) {
        Adj.get(src).add(new Edge(src, dest, cost));
        Adj.get(dest).add(new Edge(dest, src, cost));
    }
}

public class PrimsAlgorithm {
    public static void prims(Graph gph) {
        int[] previous = new int[gph.count];
        int[] dist = new int[gph.count];
        boolean[] visited = new boolean[gph.count];
        int source = 0;

        for (int i = 0; i < gph.count; i++) {
            previous[i] = -1;
            dist[i] = Integer.MAX_VALUE; // Use Integer.MAX_VALUE to represent infinity
        }

        dist[source] = 0;
        previous[source] = -1;
        EdgeComparator comp = new EdgeComparator();
        PriorityQueue<Edge> queue = new PriorityQueue<>(100, comp);
        queue.add(new Edge(source, 0));

        while (!queue.isEmpty()) {
            Edge node = queue.poll();
            source = node.dest;
            if (visited[source]) continue;
            visited[source] = true;

            List<Edge> adl = gph.Adj.get(source);
            for (Edge adn : adl) {
                int dest = adn.dest;
                int alt = adn.cost;
                if (!visited[dest] && dist[dest] > alt) {
                    dist[dest] = alt;
                    previous[dest] = source;
                    queue.add(new Edge(dest, alt));
                }
            }
        }

        // Print the result
        for (int i = 0; i < gph.count; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("node id " + i + " prev " + previous[i] + " distance : Unreachable");
            } else {
                System.out.println("node id " + i + " prev " + previous[i] + " distance : " + dist[i]);
            }
        }
    }
    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addUndirectedEdge(0, 1, 4);
        graph.addUndirectedEdge(0, 2, 4);
        graph.addUndirectedEdge(1, 2, 2);
        graph.addUndirectedEdge(1, 3, 5);
        graph.addUndirectedEdge(2, 3, 5);
        graph.addUndirectedEdge(2, 4, 11);
        graph.addUndirectedEdge(3, 4, 1);
        graph.addUndirectedEdge(3, 5, 2);
        graph.addUndirectedEdge(4, 5, 7);
        prims(graph);
    }
}

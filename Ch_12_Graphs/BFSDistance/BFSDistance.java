package Ch_12_Graphs.BFSDistance;

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

public class BFSDistance {
    public static int bfsDistance(Graph gph, int source, int dest) {
        int count = gph.count;
        boolean[] visited = new boolean[count];
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        int[] level = new int[count];
        level[source] = 0;

        while (!queue.isEmpty()) {
            int curr = queue.remove();
            int depth = level[curr];
            LinkedList<Edge> adl = gph.Adj.get(curr);

            for (Edge adn : adl) {
                if (adn.dest == dest) {
                    return depth + 1;
                }
                if (!visited[adn.dest]) {
                    visited[adn.dest] = true;
                    queue.add(adn.dest);
                    level[adn.dest] = depth + 1;
                }
            }
        }
        return -1; // If destination is not reachable from source
    }

    public static void main(String[] args) {
        Graph gph = new Graph(6);
        gph.addDirectedEdge(0, 1, 1);
        gph.addDirectedEdge(0, 2, 1);
        gph.addDirectedEdge(1, 3, 1);
        gph.addDirectedEdge(2, 4, 1);
        gph.addDirectedEdge(3, 5, 1);
        gph.addDirectedEdge(4, 5, 1);
        gph.addDirectedEdge(1, 2, 1);  // Adding more edges for multiple paths
        gph.print();

        int src = 0, dest = 5;
        int distance = bfsDistance(gph, src, dest);
        if (distance != -1) {
            System.out.println("The distance between node " + src + " and node " + dest + " is " + distance);
        } else {
            System.out.println("There is no path between node " + src + " and node " + dest);
        }
    }
}


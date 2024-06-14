package Ch_12_Graphs.PathCount;

//Problem: Given a source vertex and a destination vertex, find all the possible paths from source to destination.

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

public class GraphPathCount {
    public static int countAllPathDFS(Graph gph, boolean[] visited, int source, int dest) {
        if (source == dest) {
            return 1;
        }
        int count = 0;
        visited[source] = true;
        LinkedList<Edge> adl = gph.Adj.get(source);
        for (Edge adn : adl) {
            if (!visited[adn.dest]) {
                count += countAllPathDFS(gph, visited, adn.dest, dest);
            }
        }
        visited[source] = false;  // Backtrack
        return count;
    }

    public static int countAllPath(Graph gph, int src, int dest) {
        int count = gph.count;
        boolean[] visited = new boolean[count];
        return countAllPathDFS(gph, visited, src, dest);
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
        System.out.println("Total paths from " + src + " to " + dest + ": " + countAllPath(gph, src, dest));
    }
}

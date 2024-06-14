package Ch_12_Graphs.TransitiveClosure;
//Problem: Given a directed graph, construct a transitive closure matrix or
//reachability matrix. Vertex v is reachable from vertex u if there is a path from u
//to v.
//Transitive closure of a graph G is a graph G’, which contains the same set of
//vertices as G and whenever there is a path from vertex u to vertex v in G there is
//an edge from u to v in G’.
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

public class TransitiveClosure {
    public static void transitiveClosureUtil(Graph gph, int source, int dest, int[][] tc) {
        tc[source][dest] = 1;
        LinkedList<Edge> adl = gph.Adj.get(dest);
        for (Edge adn : adl) {
            if (tc[source][adn.dest] == 0) {
                transitiveClosureUtil(gph, source, adn.dest, tc);
            }
        }
    }

    public static int[][] transitiveClosure(Graph gph) {
        int count = gph.count;
        int[][] tc = new int[count][count];
        for (int i = 0; i < count; i++) {
            transitiveClosureUtil(gph, i, i, tc);
        }
        return tc;
    }

    public static void main(String[] args) {
        Graph gph = new Graph(4);
        gph.addDirectedEdge(0, 1, 1);
        gph.addDirectedEdge(0, 2, 1);
        gph.addDirectedEdge(1, 2, 1);
        gph.addDirectedEdge(2, 0, 1);
        gph.addDirectedEdge(2, 3, 1);
        gph.addDirectedEdge(3, 3, 1);
        gph.print();

        int[][] tc = transitiveClosure(gph);

        System.out.println("Transitive closure matrix is:");
        for (int i = 0; i < tc.length; i++) {
            for (int j = 0; j < tc.length; j++) {
                System.out.print(tc[i][j] + " ");
            }
            System.out.println();
        }
    }
}

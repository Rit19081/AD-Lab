package Ch_12_Graphs.TransposeGraph;
//Problem: Transpose of a Graph G is a graph G’ that has the same set of vertices,
//but the direction of edges is reversed.
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

public class TransposeGraph {
    public static Graph transposeGraph(Graph gph) {
        int count = gph.count;
        Graph g = new Graph(count);

        for (int i = 0; i < count; i++) {
            LinkedList<Edge> adl = gph.Adj.get(i);
            for (Edge adn : adl) {
                int dest = adn.dest;
                g.addDirectedEdge(dest, i, adn.weight); // Reverse the edge direction
            }
        }

        return g;
    }

    public static void main(String[] args) {
        Graph gph = new Graph(5);
        gph.addDirectedEdge(0, 1, 1);
        gph.addDirectedEdge(0, 2, 1);
        gph.addDirectedEdge(1, 3, 1);
        gph.addDirectedEdge(2, 1, 1);
        gph.addDirectedEdge(2, 4, 1);
        gph.addDirectedEdge(3, 4, 1);
        gph.addDirectedEdge(4, 0, 1);
        gph.print();
        System.out.println("Transposed Graph:");
        Graph transposed = transposeGraph(gph);
        transposed.print();
    }
}

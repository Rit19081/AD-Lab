package Ch_12_Graphs.CycleDetectionUndirected;
//Problem: Find if there is a cycle in an undirected graph
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

    void addUndirectedEdge(int src, int dest, int weight) {
        Edge edge1 = new Edge(src, dest, weight);
        Edge edge2 = new Edge(dest, src, weight);
        Adj.get(src).add(edge1);
        Adj.get(dest).add(edge2);
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

public class CycleDetection {
    public static boolean isCyclePresentUndirectedDFS(Graph graph, int index, int parentIndex, boolean[] visited) {
        visited[index] = true;
        LinkedList<Edge> adl = graph.Adj.get(index);

        for (Edge adn : adl) {
            int dest = adn.dest;
            if (!visited[dest]) {
                if (isCyclePresentUndirectedDFS(graph, dest, index, visited)) {
                    return true;
                }
            } else if (parentIndex != dest) {
                return true;
            }
        }
        return false;
    }

    public static boolean isCyclePresentUndirected(Graph graph) {
        int count = graph.count;
        boolean[] visited = new boolean[count];

        for (int i = 0; i < count; i++) {
            if (!visited[i]) {
                if (isCyclePresentUndirectedDFS(graph, i, -1, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Graph gph = new Graph(6);
        gph.addUndirectedEdge(0, 1, 1);
        gph.addUndirectedEdge(0, 2, 1);
        gph.addUndirectedEdge(1, 3, 1);
        gph.addUndirectedEdge(2, 4, 1);
        gph.addUndirectedEdge(4, 5, 1);
        gph.addUndirectedEdge(1, 2, 1);  // Adding more edges to create a cycle
        gph.print();

        boolean hasCycle = isCyclePresentUndirected(gph);
        if (hasCycle) {
            System.out.println("The graph contains a cycle.");
        } else {
            System.out.println("The graph does not contain a cycle.");
        }
    }
}

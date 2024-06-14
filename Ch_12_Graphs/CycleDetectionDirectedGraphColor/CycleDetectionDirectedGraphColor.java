package Ch_12_Graphs.CycleDetectionDirectedGraphColor;
//Find if there is a cycle in a graph using colour method.
//In colour method, initially visited array is assigned the value “white” which
//means that nodes are not visited. When we visit a node, we mark its colour as
//“Grey”. Nodes that are currently in visited path remain “Grey” and when all the
//connected nodes are traversed, and then the colour is changed to “Black”. If a
//node that is marked “Grey” is visited again, then there is a cycle in that path.
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

public class CycleDetectionDirectedGraphColor {
    public static boolean isCyclePresentDFSColor(Graph graph, int index, int[] visited) {
        visited[index] = 1; // Mark as grey

        LinkedList<Edge> adl = graph.Adj.get(index);
        for (Edge adn : adl) {
            int dest = adn.dest;
            if (visited[dest] == 1) { // If destination is grey, cycle detected
                return true;
            }
            if (visited[dest] == 0) { // If destination is white
                if (isCyclePresentDFSColor(graph, dest, visited)) {
                    return true;
                }
            }
        }

        visited[index] = 2; // Mark as black when fully explored
        return false;
    }

    public static boolean isCyclePresentColor(Graph graph) {
        int count = graph.count;
        int[] visited = new int[count];

        for (int i = 0; i < count; i++) {
            if (visited[i] == 0) { // If vertex is white
                if (isCyclePresentDFSColor(graph, i, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Graph gph = new Graph(6);
        gph.addDirectedEdge(0, 1, 1);
        gph.addDirectedEdge(0, 2, 1);
        gph.addDirectedEdge(1, 3, 1);
        gph.addDirectedEdge(2, 4, 1);
        gph.addDirectedEdge(3, 5, 1);
        gph.addDirectedEdge(4, 5, 1);
        gph.print();

        boolean hasCycle = isCyclePresentColor(gph);
        if (hasCycle) {
            System.out.println("The graph contains a cycle.");
        } else {
            System.out.println("The graph does not contain a cycle.");
        }
    }
}

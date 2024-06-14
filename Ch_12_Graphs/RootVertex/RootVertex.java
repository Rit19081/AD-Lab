package Ch_12_Graphs.RootVertex;
//Problem: Find Root vertex in a graph. The root vertex is the vertex, which have
//path of all other vertices in a graph. If there are multiple root vertex, then return
//any one of them.
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

public class RootVertex {
    public static void dfsUtil(Graph gph, int index, boolean[] visited, Stack<Integer> stack) {
        visited[index] = true;
        LinkedList<Edge> adl = gph.Adj.get(index);
        for (Edge adn : adl) {
            if (!visited[adn.dest]) {
                dfsUtil(gph, adn.dest, visited, stack);
            }
        }
        stack.push(index);
    }

    public static int rootVertex(Graph gph) {
        int count = gph.count;
        boolean[] visited = new boolean[count];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < count; i++) {
            if (!visited[i]) {
                dfsUtil(gph, i, visited, stack);
            }
        }

        int potentialRoot = stack.peek();
        Arrays.fill(visited, false);
        dfsUtil(gph, potentialRoot, visited, new Stack<>());

        for (boolean visitStatus : visited) {
            if (!visitStatus) {
                System.out.println("No root vertex found.");
                return -1;
            }
        }

        System.out.println("Root vertex is :: " + potentialRoot);
        return potentialRoot;
    }

    public static void main(String[] args) {
        Graph gph = new Graph(6);
        gph.addDirectedEdge(5, 2, 1);
        gph.addDirectedEdge(5, 0, 1);
        gph.addDirectedEdge(4, 0, 1);
        gph.addDirectedEdge(4, 1, 1);
        gph.addDirectedEdge(2, 3, 1);
        gph.addDirectedEdge(3, 1, 1);
        gph.print();

        rootVertex(gph);
    }
}

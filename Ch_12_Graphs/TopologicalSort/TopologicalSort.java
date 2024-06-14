package Ch_12_Graphs.TopologicalSort;

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

public class TopologicalSort {
    public static void topologicalSort(Graph gph) {
        Stack<Integer> stk = new Stack<>();
        int count = gph.count;
        boolean[] visited = new boolean[count];
        for (int i = 0; i < count; i++) {
            if (!visited[i]) {
                dfsUtil2(gph, i, visited, stk);
            }
        }
        System.out.print("Topological Sort: ");
        while (!stk.isEmpty()) {
            System.out.print(" " + stk.pop());
        }
        System.out.println();
    }

    public static void dfsUtil2(Graph gph, int index, boolean[] visited, Stack<Integer> stk) {
        visited[index] = true;
        LinkedList<Edge> adl = gph.Adj.get(index);
        for (Edge adn : adl) {
            if (!visited[adn.dest]) {
                dfsUtil2(gph, adn.dest, visited, stk);
            }
        }
        stk.push(index);
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
        topologicalSort(gph);
    }
}

package Ch_12_Graphs.GraphPathPrint;

//Problem: Print all the paths from source vertex to the destination vertex.

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

public class GraphPathPrint {
    public static void printAllPathDFS(Graph gph, boolean[] visited, int source, int dest, Stack<Integer> path) {
        path.push(source);
        if (source == dest) {
            System.out.println(path);
            path.pop();
            return;
        }
        visited[source] = true;
        LinkedList<Edge> adl = gph.Adj.get(source);
        for (Edge adn : adl) {
            if (!visited[adn.dest]) {
                printAllPathDFS(gph, visited, adn.dest, dest, path);
            }
        }
        visited[source] = false;
        path.pop();
    }

    public static void printAllPath(Graph gph, int src, int dest) {
        boolean[] visited = new boolean[gph.count];
        Stack<Integer> path = new Stack<>();
        printAllPathDFS(gph, visited, src, dest, path);
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
        System.out.println("All paths from " + src + " to " + dest + ":");
        printAllPath(gph, src, dest);
    }
}


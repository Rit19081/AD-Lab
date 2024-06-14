package Ch_12_Graphs.HamiltonianPathCycle;

import java.util.Arrays;
import java.util.LinkedList;

class GraphAM {
    int[][] adj;  // Adjacency matrix
    int count;    // Number of vertices

    GraphAM(int count) {
        this.count = count;
        adj = new int[count][count];
    }

    // Method to add an edge to the graph
    void addDirectedEdge(int src, int dest, int weight) {
        adj[src][dest] = weight;
    }

    // Method to add an undirected edge to the graph
    void addUndirectedEdge(int src, int dest, int weight) {
        adj[src][dest] = weight;
        adj[dest][src] = weight;
    }
}

public class HamiltonianPathCycle {
    // Hamiltonian Path Utility Function
    public static boolean hamiltonianPathUtil(GraphAM graph, int path[], int pSize, int added[]) {
        if (pSize == graph.count) {
            return true;
        }

        for (int vertex = 0; vertex < graph.count; vertex++) {
            if (pSize == 0 || (graph.adj[path[pSize - 1]][vertex] == 1 && added[vertex] == 0)) {
                path[pSize++] = vertex;
                added[vertex] = 1;

                if (hamiltonianPathUtil(graph, path, pSize, added))
                    return true;

                pSize--;
                added[vertex] = 0;
            }
        }
        return false;
    }

    // Hamiltonian Path Function
    public static boolean hamiltonianPath(GraphAM graph) {
        int[] path = new int[graph.count];
        int[] added = new int[graph.count];
        Arrays.fill(path, -1); // Initialize path with -1
        Arrays.fill(added, 0); // Initialize added with 0

        if (hamiltonianPathUtil(graph, path, 0, added)) {
            System.out.println("Hamiltonian Path found:");
            for (int i = 0; i < graph.count; i++) {
                System.out.print(" " + path[i]);
            }
            System.out.println();
            return true;
        }

        System.out.println("Hamiltonian Path not found");
        return false;
    }

    // Hamiltonian Cycle Utility Function
    public static boolean hamiltonianCycleUtil(GraphAM graph, int path[], int pSize, int added[]) {
        if (pSize == graph.count) {
            if (graph.adj[path[pSize - 1]][path[0]] == 1) {
                path[pSize] = path[0];
                return true;
            } else
                return false;
        }

        for (int vertex = 0; vertex < graph.count; vertex++) {
            if (pSize == 0 || (graph.adj[path[pSize - 1]][vertex] == 1 && added[vertex] == 0)) {
                path[pSize++] = vertex;
                added[vertex] = 1;

                if (hamiltonianCycleUtil(graph, path, pSize, added))
                    return true;

                pSize--;
                added[vertex] = 0;
            }
        }
        return false;
    }

    // Hamiltonian Cycle Function
    public static boolean hamiltonianCycle(GraphAM graph) {
        int[] path = new int[graph.count + 1];
        int[] added = new int[graph.count];
        Arrays.fill(path, -1); // Initialize path with -1
        Arrays.fill(added, 0); // Initialize added with 0

        if (hamiltonianCycleUtil(graph, path, 0, added)) {
            System.out.println("Hamiltonian Cycle found:");
            for (int i = 0; i <= graph.count; i++) {
                System.out.print(" " + path[i]);
            }
            System.out.println();
            return true;
        }

        System.out.println("Hamiltonian Cycle not found");
        return false;
    }

    public static void main(String[] args) {
        int count = 5;
        GraphAM graph = new GraphAM(count);
        int[][] adj = {
                { 0, 1, 0, 1, 0 },
                { 1, 0, 1, 1, 0 },
                { 0, 1, 0, 0, 1 },
                { 1, 1, 0, 0, 1 },
                { 0, 1, 1, 1, 0 } };
        for (int i = 0; i < count; i++)
            for (int j = 0; j < count; j++)
                if (adj[i][j] == 1)
                    graph.addUndirectedEdge(i, j, 1);

        System.out.println("hamiltonianPath : " + hamiltonianPath(graph));
        System.out.println("hamiltonianCycle : " + hamiltonianCycle(graph));

        GraphAM graph2 = new GraphAM(count);
        int[][] adj2 = {
                { 0, 1, 0, 1, 0 },
                { 1, 0, 1, 1, 0 },
                { 0, 1, 0, 0, 1 },
                { 1, 1, 0, 0, 0 },
                { 0, 1, 1, 0, 0 } };
        for (int i = 0; i < count; i++)
            for (int j = 0; j < count; j++)
                if (adj2[i][j] == 1)
                    graph2.addUndirectedEdge(i, j, 1);

        System.out.println("hamiltonianPath : " + hamiltonianPath(graph2));
        System.out.println("hamiltonianCycle : " + hamiltonianCycle(graph2));
    }
}

package Ch_12_Graphs.HamiltonianPath;

import java.util.Arrays;

class GraphAM {
    int[][] adj;  // Adjacency matrix
    int count;    // Number of vertices

    GraphAM(int count) {
        this.count = count;
        adj = new int[count][count];
    }

    // Method to add an edge to the graph
    void addEdge(int src, int dest) {
        adj[src][dest] = 1;
        adj[dest][src] = 1;  // Since the graph is undirected
    }
}

public class HamiltonianPath {
    public static boolean hamiltonianPathUtil(GraphAM graph, int path[], int pSize, int added[]) {
        // Base case: Full length path is found
        if (pSize == graph.count) {
            return true;
        }

        // Try to extend the path with each vertex
        for (int vertex = 0; vertex < graph.count; vertex++) {
            // There is a path from the last element and the next vertex
            // and the next vertex is not already included in the path
            if (pSize == 0 || (graph.adj[path[pSize - 1]][vertex] == 1 && added[vertex] == 0)) {
                path[pSize++] = vertex;  // Add vertex to path
                added[vertex] = 1;       // Mark vertex as added

                // Recursively build the path
                if (hamiltonianPathUtil(graph, path, pSize, added)) {
                    return true;
                }

                // Backtracking
                pSize--;                // Remove vertex from path
                added[vertex] = 0;      // Unmark vertex
            }
        }
        return false;
    }

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

    public static void main(String[] args) {
        // Example graph
        GraphAM graph = new GraphAM(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        hamiltonianPath(graph);
    }
}

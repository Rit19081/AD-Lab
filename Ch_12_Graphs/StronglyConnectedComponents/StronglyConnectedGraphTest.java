package Ch_12_Graphs.StronglyConnectedComponents;
//Strongly Connected Components: A directed graph may have different subgraphs that are strongly connected. These sub-graphs are called strongly
//connected components. In the below graph the whole graph is not strongly
//connected but its two subgraphs are strongly connected components.
import java.util.*;

class Edge {
    int src, dest;

    Edge(int src, int dest) {
        this.src = src;
        this.dest = dest;
    }
}

class Graph {
    int count;
    List<List<Integer>> adj;

    Graph(int count) {
        this.count = count;
        adj = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            adj.add(new ArrayList<>());
        }
    }

    void addDirectedEdge(int src, int dest) {
        adj.get(src).add(dest);
    }

    List<List<Integer>> getTranspose() {
        List<List<Integer>> transpose = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            transpose.add(new ArrayList<>());
        }
        for (int i = 0; i < count; i++) {
            for (int dest : adj.get(i)) {
                transpose.get(dest).add(i);
            }
        }
        return transpose;
    }

    void dfs(int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;
        for (int neighbor : adj.get(v)) {
            if (!visited[neighbor]) {
                dfs(neighbor, visited, stack);
            }
        }
        stack.push(v);
    }

    void dfsTranspose(int v, boolean[] visited, List<List<Integer>> transpose) {
        visited[v] = true;
        System.out.print(v + " ");
        for (int neighbor : transpose.get(v)) {
            if (!visited[neighbor]) {
                dfsTranspose(neighbor, visited, transpose);
            }
        }
    }

    void stronglyConnectedComponents() {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[count];

        // Fill vertices in stack according to their finishing times
        for (int i = 0; i < count; i++) {
            if (!visited[i]) {
                dfs(i, visited, stack);
            }
        }

        // Get the transpose of the graph
        List<List<Integer>> transpose = getTranspose();

        // Mark all vertices as not visited (For second DFS)
        Arrays.fill(visited, false);

        // Process all vertices in order defined by Stack
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (!visited[v]) {
                // Print Strongly connected component of the popped vertex
                dfsTranspose(v, visited, transpose);
                System.out.println();
            }
        }
    }
}

public class StronglyConnectedGraphTest {
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addDirectedEdge(0, 2);
        graph.addDirectedEdge(2, 1);
        graph.addDirectedEdge(1, 0);
        graph.addDirectedEdge(0, 3);
        graph.addDirectedEdge(3, 4);

        System.out.println("Strongly Connected Components:");
        graph.stronglyConnectedComponents();
    }
}

import java.util.LinkedList;
class Graph{
    int count;
    LinkedList<LinkedList<Edge>> Adj;
    static class Edge {
        int dest;
        int cost;
        public Edge(int dst, int cst) {
            dest = dst;
            cost = cst;
        }
    }
    public Graph(int cnt) {
        count = cnt;
        Adj = new LinkedList<LinkedList<Edge>>();
        for (int i = 0; i < cnt; i++) {
            Adj.add(new LinkedList<Edge>());
        }
    }
    void addDirectedEdge(int source, int dest, int cost) {
        Edge edge = new Edge(dest, cost);
        Adj.get(source).add(edge);
    }
    public void addDirectedEdge(int source, int dest) {
        addDirectedEdge(source, dest, 1);
    }
    public void addUndirectedEdge(int source, int dest, int cost) {
        addDirectedEdge(source, dest, cost);
        addDirectedEdge(dest, source, cost);
    }
    public void addUndirectedEdge(int source, int dest) {
        addUndirectedEdge(source, dest, 1);
    }
    public static boolean dfs(Graph gph, int source, int target) {
        int count = gph.count;
        boolean[] visited = new boolean[count];
        dfsUtil(gph, source, visited);
        return visited[target];
    }
    public static void dfsUtil(Graph gph, int index, boolean[] visited) {
        visited[index] = true;
        LinkedList<Edge> adl = gph.Adj.get(index);
        for (Edge adn : adl) {
            if (!visited[adn.dest]) {
                dfsUtil(gph, adn.dest, visited);
            }
        }
    }

    public void print() {
        for (int i = 0; i < count; i++) {
            LinkedList<Edge> ad = Adj.get(i);
            System.out.print("Vertex " + i + " is connected to : ");
            for (Edge adn : ad) {
                System.out.print("(" + adn.dest + ", " + adn.cost + ") ");
            }
            System.out.println();
        }
    }
}
public class DFS {
    public static void main(String[] args) {
        Graph gph = new Graph(4);
        gph.addUndirectedEdge(0, 1, 1);
        gph.addUndirectedEdge(0, 2, 1);
        gph.addUndirectedEdge(1, 2, 1);
        gph.addUndirectedEdge(2, 3, 1);
        gph.print();

        int source = 1;
        int target = 3;
        boolean isReachable = Graph.dfs(gph, source, target);

        if (isReachable) {
            System.out.println("The target vertex " + target + " is reachable from the source vertex " + source);
        } else {
            System.out.println("The target vertex " + target + " is not reachable from the source vertex " + source);
        }
    }
}
import java.util.*;
public class BFS {
    int count;
    LinkedList<LinkedList<Edge>> Adj;

    class Edge {
        int src;
        int dest;
        int cost;

        public Edge(int src, int dest, int cost) {
            this.src = src;
            this.dest = dest;
            this.cost = cost;
        }
    }

    public BFS(int cnt) {
        count = cnt;
        Adj = new LinkedList<>();
        for (int i = 0; i < cnt; i++) {
            Adj.add(new LinkedList<>());
        }
    }

    public void addDirectedEdge(int source, int dest, int cost) {
        Edge edge = new Edge(source, dest, cost);
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

    public static boolean bfs(BFS gph, int source, int target) {
        int count = gph.count;
        boolean[] visited = new boolean[count];
        LinkedList<Integer> que = new LinkedList<>();
        que.add(source);
        visited[source] = true;
        while (!que.isEmpty()) {
            int curr = que.remove();
            LinkedList<Edge> adl = gph.Adj.get(curr);
            for (Edge adn : adl) {
                if (!visited[adn.dest]) {
                    visited[adn.dest] = true;
                    que.add(adn.dest);
                }
            }
        }
        return visited[target];
    }

    public void primsAlgorithm() {
        boolean[] inTree = new boolean[count];
        int[] minWeight = new int[count];
        int[] parent = new int[count];

        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.cost - e2.cost);

        // Initialize minWeight with infinity and parent with -1
        for (int i = 0; i < count; i++) {
            minWeight[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }

        // Start from vertex 0
        int source = 0;
        minWeight[source] = 0;
        pq.offer(new Edge(source, 0, 0));

        while (!pq.isEmpty()) {
            Edge minEdge = pq.poll();
            int u = minEdge.dest;
            inTree[u] = true;

            for (Edge e : Adj.get(u)) {
                int v = e.dest;
                int weight = e.cost;
                if (!inTree[v] && weight < minWeight[v]) {
                    minWeight[v] = weight;
                    parent[v] = u;
                    pq.offer(new Edge(u, v, weight));
                }
            }
        }

        // Print the edges of the minimum spanning tree
        for (int i = 1; i < count; i++) {
            System.out.println("Edge: " + parent[i] + " - " + i + ", Cost: " + minWeight[i]);
        }
    }

    public void kruskalsAlgorithm() {
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.cost - e2.cost);
        UnionFind uf = new UnionFind(count);

        for (LinkedList<Edge> edges : Adj) {
            for (Edge edge : edges) {
                pq.add(edge);
            }
        }

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (!uf.connected(edge.src, edge.dest)) {
                System.out.println("Edge: " + edge.src + " - " + edge.dest + ", Cost: " + edge.cost);
                uf.union(edge.src, edge.dest);
            }
        }
    }

    public void dijkstrasAlgorithm(int source) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.cost - e2.cost);
        int[] dist = new int[count];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] visited = new boolean[count];
        dist[source] = 0;
        pq.add(new Edge(source, source, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            if (visited[current.dest]) continue;
            visited[current.dest] = true;
            for (Edge neighbor : Adj.get(current.dest)) {
                if (!visited[neighbor.dest] && dist[current.dest] + neighbor.cost < dist[neighbor.dest]) {
                    dist[neighbor.dest] = dist[current.dest] + neighbor.cost;
                    pq.add(new Edge(neighbor.src, neighbor.dest, dist[neighbor.dest]));
                }
            }
        }

        for (int i = 0; i < count; i++) {
            if (i != source) {
                System.out.println("Shortest path from " + source + " to " + i + " is " + dist[i]);
            }
        }
    }

    static class UnionFind {
        int[] parent, rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return;
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    public static void main(String[] args) {
        BFS gph = new BFS(5);
        gph.addUndirectedEdge(0, 1, 3);
        gph.addUndirectedEdge(0, 4, 2);
        gph.addUndirectedEdge(1, 2, 1);
        gph.addUndirectedEdge(2, 3, 1);
        gph.addUndirectedEdge(1, 4, 2);
        gph.addUndirectedEdge(3, 4, 1);
        gph.print();
        int source = 4;
        int target = 1;
        System.out.println("Traversal From source: " + source + " to target: " + target + " is " +BFS.bfs(gph, source, target));
        System.out.println("Prim's Algorithm:");
        gph.primsAlgorithm();
        System.out.println();
        System.out.println("Kruskal's Algorithm:");
        gph.kruskalsAlgorithm();
        System.out.println();
        System.out.println("Dijkstra's Algorithm:");
        gph.dijkstrasAlgorithm(0);
    }
}
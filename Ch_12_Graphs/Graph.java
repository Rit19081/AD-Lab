package Ch_12_Graphs;

class AdjancencyMatrix{
    int v;
    boolean matrix[][];
    public AdjancencyMatrix(int v){
        this.v = v;
        matrix = new boolean [v][v];
    }
    public void addEdges(int src, int dest){
        matrix[src][dest] = true;
        matrix[dest][src] = true;
    }
    public void print(){
        System.out.println("Adjancency Matrix: ");
        for(int i = 0 ; i<v ; i++){
            for(int j = 0 ; j < v ; j++){
                System.out.print(matrix[i][j] ? 1 + " " : 0 + " ");
            }
            System.out.println();
        }
    }
}
class Node{
    int data;
    Node next;
    Node (int data){
        this.data = data;
    }
}
class AdjancencyList{
    int v;
    Node [] list;
    AdjancencyList(int v){
        this.v = v;
        list = new Node[v];
        for(int i = 0 ; i < v ; i++){
            list[i] = null;
        }
    }
    public void addEdges(int src,int dest){
        Node newNode = new Node(dest);
        newNode.next = list[src];
        list[src] = newNode;

        newNode = new Node(src);
        newNode.next = list[dest];
        list[dest] = newNode;
    }
    public void print(){
        for(int i = 0 ; i < v ; i++){
            System.out.print(i + " ");
            Node temp = list[i];
            while(temp != null){
                System.out.print("-> " + temp.data);
                temp = temp.next;
            }
            System.out.println();
        }
    }
    public void bfs(int start){
        boolean [] visited = new boolean[v];
        int [] queue = new int[v];
        int front = 0 , rear = 0;
        visited [start] = true;
        queue[rear++] = start;
        while(front < rear){
            int vertex = queue[front++];
            System.out.print(vertex + " ");
            Node temp = list[vertex];
            while(temp != null){
                int adjencencyVertex = temp.data;
                if(!visited[adjencencyVertex]){
                    visited[adjencencyVertex] = true;
                    queue[rear++] = adjencencyVertex;
                }
                temp = temp.next;
            }
        }
        System.out.println();
    }
    public void dfs (int start){
        boolean [] visited = new boolean[v];
        int [] stack = new int[v];
        int top = -1;
        visited[start] = true;
        stack[++top] = start;
        while (top >=0){
            int vertex = stack[top--];
            System.out.print(vertex + " ");
            Node temp = list[vertex];
            while(temp != null){
                int adjencencyVertex = temp.data;
                if (!visited[adjencencyVertex]){
                    visited[adjencencyVertex] = true;
                    stack [++top] = adjencencyVertex;
                }
                temp = temp.next;
            }
        }
        System.out.println();
    }
}

public class Graph {
    public static void main(String[] args) {
        AdjancencyMatrix a = new AdjancencyMatrix(5);
        a.addEdges(1,4);
        a.print();
        AdjancencyList b  = new AdjancencyList(5);
        b.addEdges(1,4);
        b.addEdges(2,3);
        b.addEdges(1,2);
        b.print();
        b.bfs(2);
        b.dfs(2);
    }
}

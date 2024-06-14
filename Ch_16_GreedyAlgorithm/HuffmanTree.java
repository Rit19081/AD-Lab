package Ch_16_GreedyAlgorithm;

import java.util.PriorityQueue;

public class HuffmanTree {
    Node root = null;

    class Node implements Comparable<Node> {
        char ch;
        int freq;
        Node left;
        Node right;

        Node(char ch, int freq, Node l, Node r) {
            this.ch = ch;
            this.freq = freq;
            left = l;
            right = r;
        }

        public int compareTo(Node r) {
            return this.freq - r.freq;
        }
    }

    public void HuffmanTree(char arr[], int freq[]) {
        int n = arr.length;
        PriorityQueue<Node> que = new PriorityQueue<Node>(n);
        for (int i = 0; i < n; i++) {
            Node node = new Node(arr[i], freq[i], null, null);
            que.add(node);
        }

        while (que.size() > 1) {
            Node lt = que.peek();
            que.poll();
            Node rt = que.peek();
            que.poll();

            Node nd = new Node('-', lt.freq + rt.freq, lt, rt);
            que.add(nd);
        }

        root = que.peek();
    }

    private void print(Node root, String s) {
        if (root != null && root.left == null && root.right == null && root.ch != '-') {
            System.out.println(root.ch + " = " + s);
            return;
        }
        print(root.left, s + "0");
        print(root.right, s + "1");
    }

    public void print() {
        System.out.println("Char = Huffman code");
        print(root, "");
    }

    public static void main(String[] args) {
        char arr[] = { 'A', 'B', 'C', 'D', 'E' };
        int freq[] = { 30, 25, 21, 14, 10 };
        HuffmanTree hf = new HuffmanTree();
        hf.HuffmanTree(arr, freq);
        hf.print();
    }
}

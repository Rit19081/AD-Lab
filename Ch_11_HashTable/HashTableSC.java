package Ch_11_HashTable;
//Example 11.10: Below is separate chaining implementation of hash tables.
public class HashTableSC {
    private int tableSize;
    Node[] listArray;

    private class Node {
        private int value;
        private Node next;

        public Node(int v, Node n) {
            value = v;
            next = n;
        }
    }

    public HashTableSC() {
        tableSize = 512;
        listArray = new Node[tableSize];
        for (int i = 0; i < tableSize; i++) {
            listArray[i] = null;
        }
    }

    private int computeHash(int key) {
        int hashValue = key;
        return hashValue % tableSize;
    }

    public void add(int value) {
        int index = computeHash(value);
        listArray[index] = new Node(value, listArray[index]);
    }

    public boolean remove(int value) {
        int index = computeHash(value);
        Node nextNode, head = listArray[index];
        if (head != null && head.value == value) {
            listArray[index] = head.next;
            return true;
        }
        while (head != null) {
            nextNode = head.next;
            if (nextNode != null && nextNode.value == value) {
                head.next = nextNode.next;
                return true;
            } else {
                head = nextNode;
            }
        }
        return false;
    }

    public void print() {
        for (int i = 0; i < tableSize; i++) {
            System.out.print("Index " + i + ": ");
            Node head = listArray[i];
            while (head != null) {
                System.out.print(head.value + " -> ");
                head = head.next;
            }
            System.out.println("null");
        }
    }

    public boolean find(int value) {
        int index = computeHash(value);
        Node head = listArray[index];
        while (head != null) {
            if (head.value == value) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    public static void main(String[] args) {
        HashTableSC ht = new HashTableSC();

        // Adding values from 100 to 109
        for (int i = 100; i < 110; i++) {
            ht.add(i);
        }

        // Checking if 100 exists, removing it, and checking again
        System.out.println("Search 100: " + ht.find(100));
        System.out.println("Remove 100: " + ht.remove(100));
        System.out.println("Search 100 after removal: " + ht.find(100));

        // Removing 100 again (which should now return false)
        System.out.println("Remove 100 again: " + ht.remove(100));

        // Printing the hash table
        ht.print();
    }
}

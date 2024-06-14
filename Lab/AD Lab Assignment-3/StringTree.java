public class StringTree {
    Node root = null;
    class Node {
        String value;
        int count;
        Node lChild;
        Node rChild;
    }
    public void print() {
        print(root);
    }
    public void print(Node curr) {
        if (curr != null) {
            System.out.print("Value is " + curr.value);
            System.out.println(", Count is " + curr.count);
            print(curr.lChild);
            print(curr.rChild);
        }
    }
    public void add(String value) {
        root = add(value, root);
    }
    Node add(String value, Node curr) {
        if (curr == null) {
            curr = new Node();
            curr.value = value;
            curr.lChild = curr.rChild = null;
            curr.count = 1;
        } else {
            int compare = value.compareTo(curr.value);
            if (compare == 0) {
                curr.count++;
            } else if (compare < 0) {
                curr.lChild = add(value, curr.lChild);
            } else {
                curr.rChild = add(value, curr.rChild);
            }
        }
        return curr;
    }
    boolean find(String value) {
        return find(root, value);
    }
    boolean find(Node curr, String value) {
        if (curr == null)
            return false;
        int compare = value.compareTo(curr.value);
        if (compare == 0)
            return true;
        else if (compare < 0)
            return find(curr.lChild, value);
        else
            return find(curr.rChild, value);
    }
    int frequency(String value) {
        return frequency(root, value);
    }
    int frequency(Node curr, String value) {
        if (curr == null)
            return 0;
        int compare = value.compareTo(curr.value);
        if (compare == 0)
            return curr.count;
        else if (compare < 0)
            return frequency(curr.lChild, value);
        else
            return frequency(curr.rChild, value);
    }
    void freeTree() {
        root = null;
    }
    public static void main(String[] args) {
        StringTree tree = new StringTree();
        tree.add("Legion");
        tree.add("Omen");
        tree.add("LOQ");
        tree.add("LOQ");
        tree.print();
        System.out.println("Is pavalion present? " + tree.find("pavalion"));
        System.out.println("Is LOQ present? " + tree.find("LOQ"));
        System.out.println("Frequency of LOQ: " + tree.frequency("LOQ"));
        tree.freeTree();
        tree.print();
    }
}

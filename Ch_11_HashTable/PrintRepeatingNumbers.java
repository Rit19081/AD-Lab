package Ch_11_HashTable;

import java.util.HashSet;

public class PrintRepeatingNumbers {

    public static void printRepeating(int[] arr) {
        HashSet<Integer> seen = new HashSet<>();
        HashSet<Integer> repeating = new HashSet<>();

        System.out.print("Repeating elements are:");
        for (int num : arr) {
            if (seen.contains(num)) {
                if (!repeating.contains(num)) {
                    System.out.print(" " + num);
                    repeating.add(num);
                }
            } else {
                seen.add(num);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 4, 5, 2, 3, 1, 1};

        printRepeating(arr);
    }
}

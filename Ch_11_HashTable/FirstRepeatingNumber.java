package Ch_11_HashTable;

import java.util.HashSet;

public class FirstRepeatingNumber {

    public static void printFirstRepeating(int[] arr) {
        int firstRepeating = Integer.MAX_VALUE;
        HashSet<Integer> hs = new HashSet<>();

        // Traverse the array from right to left
        for (int i = arr.length - 1; i >= 0; i--) {
            if (hs.contains(arr[i])) {
                firstRepeating = arr[i];
            } else {
                hs.add(arr[i]);
            }
        }

        if (firstRepeating != Integer.MAX_VALUE) {
            System.out.println("First Repeating number is : " + firstRepeating);
        } else {
            System.out.println("No repeating number found");
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 2, 1};
        int[] arr2 = {1, 2, 3, 4, 5};

        System.out.print("For arr1: ");
        printFirstRepeating(arr1); // Output: First Repeating number is : 2

        System.out.print("For arr2: ");
        printFirstRepeating(arr2); // Output: No repeating number found
    }
}

package Ch_11_HashTable;

import java.util.Arrays;
import java.util.HashSet;

public class RemoveDuplicates {
    public static int[] removeDuplicates(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0]; // Return an empty array if input is null or empty
        }

        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> duplicates = new HashSet<>();

        for (int num : arr) {
            if (!set.contains(num)) {
                set.add(num);
            } else {
                duplicates.add(num);
            }
        }

        // Create a new array to hold unique elements
        int[] result = new int[set.size()];
        int index = 0;
        for (int num : arr) {
            if (!duplicates.contains(num)) {
                result[index++] = num;
            }
        }

        // Resize result array to remove unused elements
        return Arrays.copyOf(result, index);
    }

    public static void main(String[] args) {
        int[] input1 = {1, 2, 3, 4, 2, 5, 6, 1};
        int[] input2 = {}; // Empty array case
        int[] input3 = null; // Null array case

        int[] result1 = removeDuplicates(input1);
        int[] result2 = removeDuplicates(input2);
        int[] result3 = removeDuplicates(input3);

        System.out.println("Result 1: " + Arrays.toString(result1)); // Output: [3, 4, 5, 6]
        System.out.println("Result 2: " + Arrays.toString(result2)); // Output: []
        System.out.println("Result 3: " + Arrays.toString(result3)); // Output: []
    }
}

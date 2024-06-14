package Ch_11_HashTable;

import java.util.HashSet;

public class FindMissingNumber {

    public static int findMissing(int[] arr, int start, int end) {
        HashSet<Integer> hs = new HashSet<>();

        // Add all elements of arr to the HashSet
        for (int num : arr) {
            hs.add(num);
        }

        // Check for the missing number in the range [start, end]
        for (int curr = start; curr <= end; curr++) {
            if (!hs.contains(curr)) {
                return curr;
            }
        }

        // If no missing number found, return a sentinel value
        return Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 4, 5, 6};
        int[] arr2 = {1, 2, 3, 4, 5};

        int missing1 = findMissing(arr1, 1, 6);
        int missing2 = findMissing(arr2, 1, 5);

        System.out.println("Missing number in arr1: " + missing1); // Output: 3
        System.out.println("Missing number in arr2: " + missing2); // Output: 6
    }
}

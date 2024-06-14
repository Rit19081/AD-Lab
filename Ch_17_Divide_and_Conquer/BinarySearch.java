package Ch_17_Divide_and_Conquer;

public class BinarySearch {

    // Iterative binary search
    public static int binarySearchIterative(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Check if target is present at mid
            if (arr[mid] == target)
                return mid;

            // If target is greater, ignore left half
            if (arr[mid] < target)
                left = mid + 1;
                // If target is smaller, ignore right half
            else
                right = mid - 1;
        }

        // If element is not present in array
        return -1;
    }

    // Recursive binary search
    public static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        if (left <= right) {
            int mid = left + (right - left) / 2;

            // Check if target is present at mid
            if (arr[mid] == target)
                return mid;

            // If target is greater, search in right half
            if (arr[mid] < target)
                return binarySearchRecursive(arr, target, mid + 1, right);

            // If target is smaller, search in left half
            return binarySearchRecursive(arr, target, left, mid - 1);
        }

        // If element is not present in array
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 10, 40};

        int target = 10;
        int indexIterative = binarySearchIterative(arr, target);
        int indexRecursive = binarySearchRecursive(arr, target, 0, arr.length - 1);

        System.out.println("Iterative Binary Search:");
        if (indexIterative != -1) {
            System.out.println("Element found at index " + indexIterative);
        } else {
            System.out.println("Element not found in array");
        }

        System.out.println("\nRecursive Binary Search:");
        if (indexRecursive != -1) {
            System.out.println("Element found at index " + indexRecursive);
        } else {
            System.out.println("Element not found in array");
        }
    }
}

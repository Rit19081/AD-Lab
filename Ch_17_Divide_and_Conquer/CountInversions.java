package Ch_17_Divide_and_Conquer;

public class CountInversions {
    public static long countInversions(int[] arr) {
        int[] temp = new int[arr.length];
        return mergeSort(arr, temp, 0, arr.length - 1);
    }
    private static long mergeSort(int[] arr, int[] temp, int start, int end) {
        long inversionCount = 0;
        if (start < end) {
            int mid = (start + end) / 2;
            inversionCount += mergeSort(arr, temp, start, mid);
            inversionCount += mergeSort(arr, temp, mid + 1, end);
            inversionCount += merge(arr, temp, start, mid, end);
        }
        return inversionCount;
    }
    private static long merge(int[] arr, int[] temp, int start, int mid, int end) {
        long inversionCount = 0;
        int i = start, j = mid + 1, k = start;
        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                inversionCount += mid - i + 1;
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= end) {
            temp[k++] = arr[j++];
        }
        for (int l = start; l <= end; l++) {
            arr[l] = temp[l];
        }
        return inversionCount;
    }
    public static void main(String[] args) {
        int[] arr = {8, 4, 2, 1};
        System.out.println("Number of inversions: " + countInversions(arr));
    }
}

public class IntervalScheduling {
    public static int[][] scheduleIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][];
        }
        for (int i = 0; i < intervals.length - 1; i++) {
            for (int j = 0; j < intervals.length - i - 1; j++) {
                if (intervals[j][1] > intervals[j + 1][1]) {
                    int[] temp = intervals[j];
                    intervals[j] = intervals[j + 1];
                    intervals[j + 1] = temp;
                }
            }
        }
        int[][] selectedIntervals = new int[intervals.length][2];
        int[] lastSelectedInterval = intervals[0];
        selectedIntervals[0] = lastSelectedInterval;
        int index = 1;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= lastSelectedInterval[1]) {
                selectedIntervals[index] = intervals[i];
                lastSelectedInterval = intervals[i];
                index++;
            }
        }
        int[][] result = new int[index][2];
        System.arraycopy(selectedIntervals, 0, result, 0, index);
        return result;
    }
    public static void main(String[] args) {
        int[][] intervals = {
            {1, 3},
            {2, 4},
            {3, 6},
            {5, 7},
            {8, 9}
        };
        int[][] selectedIntervals = scheduleIntervals(intervals);
        System.out.println("Selected Intervals:");
        for (int[] interval : selectedIntervals) {
            System.out.println("[" + interval[0] + ", " + interval[1] + "]");
        }
    }
}

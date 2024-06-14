package Ch_16_GreedyAlgorithm;

import java.util.Arrays;

public class ActivitySelection implements Comparable<ActivitySelection> {
    int start, stop;

    ActivitySelection(int s, int f) {
        start = s;
        stop = f;
    }

    public int compareTo(ActivitySelection s2) {
        return this.stop - s2.stop;
    }

    public void maxActivities(int s[], int f[], int n) {
        ActivitySelection act[] = new ActivitySelection[n];
        for (int i = 0; i < n; i++) {
            act[i] = new ActivitySelection(s[i], f[i]);
        }

        Arrays.sort(act); // Sort according to finish time

        int i = 0; // The first activity at index 0 is always gets selected.
        System.out.print("Activities are : (" + act[i].start + "," + act[i].stop + ")");

        for (int j = 1; j < n; j++) {
            // Find next activity whose start time is greater than or equal
            // to the finish time of previous activity.
            if (act[j].start >= act[i].stop) {
                System.out.print(", (" + act[j].start + "," + act[j].stop + ")");
                i = j;
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int s[] = { 1, 5, 0, 3, 5, 6, 8 };
        int f[] = { 2, 6, 5, 4, 9, 7, 9 };
        int n = s.length;
        ActivitySelection as = new ActivitySelection(0, 0);
        as.maxActivities(s, f, n);
    }
}

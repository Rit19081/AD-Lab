package Ch_18_Dynamic_Programming;

import java.util.Arrays;
import java.util.Comparator;

public class WeightedIntervalScheduling {

    // Class to represent a job
    static class Job {
        int start;
        int end;
        int weight;

        public Job(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    // Function to find the latest non-conflicting job
    static int latestNonConflict(Job[] jobs, int index) {
        for (int i = index - 1; i >= 0; i--) {
            if (jobs[i].end <= jobs[index].start) {
                return i;
            }
        }
        return -1;
    }

    // Function to find the maximum weight subset of mutually compatible jobs
    static int findMaxWeight(Job[] jobs, int n, int[] memo) {
        if (n == 0) {
            return 0;
        }

        if (memo[n] != -1) {
            return memo[n];
        }

        // Include the current job
        int includeWeight = jobs[n - 1].weight;
        int i = latestNonConflict(jobs, n - 1);
        if (i != -1) {
            includeWeight += findMaxWeight(jobs, i + 1, memo);
        }

        // Exclude the current job
        int excludeWeight = findMaxWeight(jobs, n - 1, memo);

        // Take the maximum of including and excluding the current job
        memo[n] = Math.max(includeWeight, excludeWeight);
        return memo[n];
    }

    // Main function to solve the weighted interval scheduling problem
    public static int weightedIntervalScheduling(Job[] jobs) {
        // Sort jobs according to their finish times
        Arrays.sort(jobs, Comparator.comparingInt(job -> job.end));

        int n = jobs.length;
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);

        return findMaxWeight(jobs, n, memo);
    }

    // Driver function to test the algorithm
    public static void main(String[] args) {
        Job[] jobs = {
                new Job(1, 2, 50),
                new Job(3, 5, 20),
                new Job(6, 19, 100),
                new Job(2, 100, 200)
        };

        System.out.println("Maximum weight of non-conflicting jobs: " + weightedIntervalScheduling(jobs));
    }
}

package Ch_17_Divide_and_Conquer;

import java.util.*;

// Point class representing a 2D point
class Point {
    double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

// Comparator to sort points by x-coordinate
class PointComparatorX implements Comparator<Point> {
    public int compare(Point p1, Point p2) {
        return Double.compare(p1.x, p2.x);
    }
}

// Comparator to sort points by y-coordinate
class PointComparatorY implements Comparator<Point> {
    public int compare(Point p1, Point p2) {
        return Double.compare(p1.y, p2.y);
    }
}

public class ClosestPair {

    // Function to compute the Euclidean distance between two points
    public static double distance(Point p1, Point p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // Function to find the closest pair of points using a divide and conquer approach
    public static double closestPair(Point[] points) {
        // Sort points by x-coordinate
        Arrays.sort(points, new PointComparatorX());

        // Call recursive function to find closest pair
        return closestPairUtil(points, 0, points.length - 1);
    }

    // Recursive function to find closest pair of points
    private static double closestPairUtil(Point[] points, int left, int right) {
        if (right - left <= 3) {
            // Base case: Brute force for small number of points
            return bruteForceClosest(points, left, right);
        }

        // Find midpoint
        int mid = (left + right) / 2;
        Point midPoint = points[mid];

        // Recursively find closest pairs in left and right halves
        double distLeft = closestPairUtil(points, left, mid);
        double distRight = closestPairUtil(points, mid + 1, right);

        // Find minimum distance from left and right halves
        double minDist = Math.min(distLeft, distRight);

        // Merge step: Find points close to the dividing line and check for closer pairs
        List<Point> strip = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (Math.abs(points[i].x - midPoint.x) < minDist) {
                strip.add(points[i]);
            }
        }

        // Sort strip by y-coordinate
        strip.sort(new PointComparatorY());

        // Check the points in the strip
        int size = strip.size();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size && (strip.get(j).y - strip.get(i).y) < minDist; j++) {
                double dist = distance(strip.get(i), strip.get(j));
                if (dist < minDist) {
                    minDist = dist;
                }
            }
        }

        return minDist;
    }

    // Brute force method to find closest pair of points for small number of points
    private static double bruteForceClosest(Point[] points, int left, int right) {
        double minDist = Double.MAX_VALUE;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                double dist = distance(points[i], points[j]);
                if (dist < minDist) {
                    minDist = dist;
                }
            }
        }
        return minDist;
    }

    public static void main(String[] args) {
        Point[] points = { new Point(2, 3), new Point(12, 30), new Point(40, 50), new Point(5, 1), new Point(12, 10),
                new Point(3, 4) };

        double closestDist = closestPair(points);
        System.out.println("Closest distance between two points: " + closestDist);
    }
}

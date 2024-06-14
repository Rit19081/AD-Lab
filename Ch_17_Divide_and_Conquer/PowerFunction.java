package Ch_17_Divide_and_Conquer;

public class PowerFunction {

    // Function to compute X^N using divide and conquer
    public static double power(double X, int N) {
        if (N == 0) {
            return 1; // X^0 is 1
        } else if (N % 2 == 0) {
            // If N is even
            double value = power(X, N / 2);
            return value * value;
        } else {
            // If N is odd
            double value = power(X, N / 2);
            return value * value * X;
        }
    }

    public static void main(String[] args) {
        double X = 2.0;
        int N = 5;

        double result = power(X, N);
        System.out.println(X + " raised to the power of " + N + " is: " + result);
    }
}

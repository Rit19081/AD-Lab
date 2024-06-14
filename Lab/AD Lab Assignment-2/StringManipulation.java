public class StringManipulation {
    static void printAllOccurrences(String text, String pattern) {
        int startPos = 0;
        while ((startPos = bruteForceSearch(text.toCharArray(), pattern.toCharArray(), startPos)) != -1) {
            System.out.println("Pattern found at position: " + startPos);
            startPos++;
        }
    }

    static int bruteForceSearch(char[] text, char[] pattern, int startPos) {
        int i = startPos, j = 0;
        final int n = text.length;
        final int m = pattern.length;
        while (i <= n - m) {
            j = 0;
            while (j < m && pattern[j] == text[i + j]) {
                j++;
            }
            if (j == m) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static void main(String[] args) {
        String a = "aabbccddabcddeeeabc";
        String b = "abc";
        System.out.println("Positions where pattern occurs:");
        printAllOccurrences(a, b);
    }
}

package Ch_11_HashTable;

import java.util.HashMap;

public class AnagramSolver {

    public static boolean isAnagram(char[] str1, char[] str2) {
        int size1 = str1.length;
        int size2 = str2.length;
        if (size1 != size2)
            return false;

        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();

        // Count frequencies in str1
        for (char ch : str1) {
            if (hm.containsKey(ch))
                hm.put(ch, hm.get(ch) + 1);
            else
                hm.put(ch, 1);
        }

        // Compare with str2
        for (char ch : str2) {
            if (!hm.containsKey(ch) || hm.get(ch) == 0)
                return false;
            else
                hm.put(ch, hm.get(ch) - 1);
        }

        return true;
    }

    public static void main(String[] args) {
        char[] str1 = "listen".toCharArray();
        char[] str2 = "silent".toCharArray();

        if (isAnagram(str1, str2)) {
            System.out.println("Strings are anagrams.");
        } else {
            System.out.println("Strings are not anagrams.");
        }

        char[] str3 = "hello".toCharArray();
        char[] str4 = "world".toCharArray();

        if (isAnagram(str3, str4)) {
            System.out.println("Strings are anagrams.");
        } else {
            System.out.println("Strings are not anagrams.");
        }
    }
}

public class RobinKarp {
	static int robinKarp(String text, String pattern) {
		return robinKarp(text.toCharArray(), pattern.toCharArray());
	}
	static int robinKarp(char[] text, char[] pattern) {
		int n = text.length;
		int m = pattern.length;
		int i, j;
		int prime = 101;
		int powm = 1;
		int TextHash = 0, PatternHash = 0;
		if (m == 0 || m > n) {
			return -1;
		}
		for (i = 0; i < m - 1; i++) {
			powm = (powm << 1) % prime;
		}
		for (i = 0; i < m; i++) {
			PatternHash = ((PatternHash << 1) + pattern[i]) % prime;
			TextHash = ((TextHash << 1) + text[i]) % prime;
		}
		for (i = 0; i <= (n - m); i++) {
			if (TextHash == PatternHash) {
				for (j = 0; j < m; j++) {
					if (text[i + j] != pattern[j]) {
						break;
					}
				}
				if (j == m)
					return i;
			}
			TextHash = (((TextHash - text[i] * powm) << 1) + text[i + m]) % prime;
			if (TextHash < 0) {
				TextHash = (TextHash + prime);
			}
		}	
		return -1;
	}
	public static void main(String[] args) {
		System.out.println(robinKarp("aaabbbcccdddabc", "abc"));
	}
}

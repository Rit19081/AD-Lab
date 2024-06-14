package Ch_18_Dynamic_Programming;

import java.util.Arrays;

public class CoinExchangeRecursiveWithMemoization {

    public static int coinExchangeRecursive(int[] coins, int amount, int[] memo) {
        if (amount == 0) return 0;
        if (memo[amount] != -1) return memo[amount];

        int minCoins = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (amount - coin >= 0) {
                int currentCoins = coinExchangeRecursive(coins, amount - coin, memo);
                if (currentCoins != Integer.MAX_VALUE) {
                    minCoins = Math.min(minCoins, currentCoins + 1);
                }
            }
        }
        memo[amount] = minCoins;
        return minCoins;
    }

    public static int coinExchange(int[] coins, int amount) {
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, -1);
        return coinExchangeRecursive(coins, amount, memo);
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println("Minimum number of coins required (recursive with memoization): " + coinExchange(coins, amount));
    }
}
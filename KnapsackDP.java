import java.util.Scanner;

public class KnapsackDP {
    public static int knapsack(int W, int wt[], int val[], int n) {
        int dp[][] = new int[n + 1][W + 1];
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (wt[i - 1] <= w) {
                    dp[i][w] = Math.max(
                            val[i - 1] + dp[i - 1][w - wt[i - 1]],
                            dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        System.out.println("Items included in knapsack:");
        int w = W;
        for (int i = n; i > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                System.out.println("Item " + i + " (Weight: " + wt[i - 1] + ", Value: " + val[i - 1] + ")");
                w = w - wt[i - 1];
            }
        }
        return dp[n][W];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n, W;
        System.out.print("Enter number of items: ");
        n = sc.nextInt();
        int wt[] = new int[n];
        int val[] = new int[n];
        System.out.println("Enter weights:");
        for (int i = 0; i < n; i++) {
            wt[i] = sc.nextInt();
        }
        System.out.println("Enter profit values:");
        for (int i = 0; i < n; i++) {
            val[i] = sc.nextInt();
        }
        System.out.print("Enter capacity of knapsack: ");
        W = sc.nextInt();
        int result = knapsack(W, wt, val, n);
        System.out.println("Maximum profit value: " + result);
        sc.close();
    }
}
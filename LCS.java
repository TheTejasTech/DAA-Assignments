// import java.util.Scanner;

// public class LCS {

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         System.out.print("Enter the first string: ");
//         String s1 = sc.nextLine();
//         System.out.print("Enter the second string: ");
//         String s2 = sc.nextLine();
//         System.out.println("Longest Common Subsequence: " + lcs(s1, s2));
//         sc.close();
//     }

//     private static int lcs(String a, String b) {
//         int m = a.length();
//         int n = b.length();

//         int[][] dp = new int[m + 1][n + 1];

//         for (int i = 1; i <= m; i++) {
//             for (int j = 1; j <= n; j++) {

//                 if (a.charAt(i - 1) == b.charAt(j - 1)) {
//                     dp[i][j] = dp[i - 1][j - 1] + 1;
//                 } else {
//                     dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
//                 }
//             }
//         }

//         int i = m, j = n;
//         StringBuilder lcsString = new StringBuilder();

//         while (i > 0 && j > 0) {
//             if (a.charAt(i - 1) == b.charAt(j - 1)) {
//                 lcsString.append(a.charAt(i - 1));
//                 i--;
//                 j--;
//             } else if (dp[i - 1][j] > dp[i][j - 1]) {
//                 i--;
//             } else {
//                 j--;
//             }
//         }

//         //System.out.println("The longest common subsequence is " + lcsString.reverse().toString());
//         String finalLCS = lcsString.reverse().toString();

//         System.out.print("The longest common subsequence is ");

//         for (int k = 0; k < finalLCS.length(); k++) {
//             System.out.print(finalLCS.charAt(k) + " ");
//         }

//         System.out.println();
//         return dp[m][n];
//     }
//
import java.util.*;

public class LCS {
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();
        String b = sc.nextLine();
        System.out.println(lcs(a, b));
    }

    public static int lcs(String a, String b) {
        int m = a.length();
        int n = b.length();
        int[][] dp = new int[m+1][n+1];
        for (int i = 1; i < a.length(); i++) {
            for (int j = 1; j < b.length()-1; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        int i = m, j = n;
        StringBuilder abc = new StringBuilder();
        while (i > 0 || j > 0) {
            if (a.charAt(i - 1) == b.charAt(i - 1)) {
                abc.append(a.charAt(i - 1));
                j--;
                i--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        System.out.println("longest common subsequence is: "+ abc.reverse().toString());
        String finalstr = abc.reverse().toString();
        System.out.println("the string is"+ finalstr);
        for (int index = 0; index < abc.length(); index++) {
            System.out.print( finalstr.charAt(index));
        }
        System.out.println();
        return dp[m][n];
    }
}
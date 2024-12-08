package string.lcs;

import java.io.BufferedReader;
import java.util.Scanner;

public class BOJ_1958 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        String s3 = sc.nextLine();
        int ans = lcs(s1, s2, s3);
        System.out.println(ans);
    }

    private static int lcs(String s1, String s2, String s3) {
        int[][][] table = new int[s3.length() + 1][s2.length() + 1][s1.length() + 1];

        for (int z = 1; z <= s3.length(); z++) {
            for (int y = 1; y <= s2.length(); y++) {
                for (int x = 1; x <= s1.length(); x++) {
                    if (s1.charAt(x - 1) == s2.charAt(y - 1) && s2.charAt(y - 1) == s3.charAt(z - 1)) { // 마지막 문자가 같다면
                        table[z][y][x] = table[z - 1][y - 1][x - 1] + 1;
                    } else {
                        table[z][y][x] = Math.max(table[z - 1][y][x], Math.max(table[z][y - 1][x], table[z][y][x - 1]));
                    }
                }
            }
        }
        return table[s3.length()][s2.length()][s1.length()];
    }
}

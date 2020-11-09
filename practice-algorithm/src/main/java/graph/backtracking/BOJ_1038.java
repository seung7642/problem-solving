package graph.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_1038 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static List<Long> decreasingNumList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i <= 9; i++) {
            dfs(i, 0);
        }

        Collections.sort(decreasingNumList);

        if (N >= 1023) System.out.println("-1");
        else System.out.println(decreasingNumList.get(N));

        br.close();
    }

    private static void dfs(long num, int depth) {
        if (depth > 10) { // 0~9까지 총 10자리까지만 가능하다.
            return;
        }

        decreasingNumList.add(num);
        for (int i = 0; i <= 9; i++) {
            if (num % 10 > i) {
                dfs(num * 10 + i, depth + 1);
            }
        }
    }
}

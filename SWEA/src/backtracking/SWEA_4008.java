package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA_4008 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N, max, min;
    private static int[] num, op;
    private static Stack<Integer> select;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(in.readLine());
            num = new int[N];
            op = new int[4];

            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < 4; i++) {
                op[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(in.readLine());
            for (int i = 0; i < N; i++) {
                num[i] = Integer.parseInt(st.nextToken());
            }

            max = 0x80000000;
            min = 0x7fffffff;
            select = new Stack<>();
            dfs(0);
            sb.append("#").append(tc).append(" ").append(max - min).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int depth) {
        if (depth == N - 1) {
            calc();
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (op[i] == 0) continue;
            op[i]--;
            select.push(i);
            dfs(depth + 1);
            select.pop();
            op[i]++;
        }
    }

    private static void calc() {
        int val = num[0];
        for (int i = 0; i < N - 1; i++) {
            switch (select.get(i)) {
                case 0: val += num[i + 1]; break;
                case 1: val -= num[i + 1]; break;
                case 2: val *= num[i + 1]; break;
                case 3: val /= num[i + 1]; break;
            }
        }
        min = Math.min(min, val);
        max = Math.max(max, val);
    }
}

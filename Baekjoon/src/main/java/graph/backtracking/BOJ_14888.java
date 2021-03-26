package graph.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 연산자 끼워넣기
public class BOJ_14888 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, maxValue = Integer.MIN_VALUE, minValue = Integer.MAX_VALUE;
    private static int[] arr;
    private static int[] operator = new int[4];

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++)
            operator[i] = Integer.parseInt(st.nextToken());

        dfs(arr[0], 1);

        System.out.println(maxValue + "\n" + minValue);
        br.close();
    }

    private static void dfs(int num, int idx) {
        if (idx == N) {
            maxValue = Math.max(maxValue, num);
            minValue = Math.min(minValue, num);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operator[i] > 0) {
                operator[i]--;

                switch (i) {
                    case 0: dfs(num + arr[idx], idx + 1); break;
                    case 1: dfs(num - arr[idx], idx + 1); break;
                    case 2: dfs(num * arr[idx], idx + 1); break;
                    case 3: dfs(num / arr[idx], idx + 1); break;
                }

                operator[i]++;
            }
        }
    }
}

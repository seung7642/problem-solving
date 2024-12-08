package graph.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1062 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, K, ans;
    private static String[] words;
    private static boolean[] visited = new boolean[26]; // 알파벳은 총 26가지.

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        words = new String[N];

        if (K < 5) {
            System.out.println("0");
            return;
        } else if (K == 26) {
            System.out.println(N);
            return;
        }

        // 무조건 배워야하는 언어
        visited[ 'a' - 'a' ] = true;
        visited[ 'c' - 'a' ] = true;
        visited[ 'i' - 'a' ] = true;
        visited[ 'n' - 'a' ] = true;
        visited[ 't' - 'a' ] = true;

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine().replaceAll("anta|tica", "");
        }

        dfs(0, 0);

        System.out.println(ans);
        br.close();
    }

    private static void dfs(int idx, int depth) {
        if (depth == K - 5) {
            int tmp = 0;
            for (int i = 0; i < N; i++) {
                boolean isReadable = true;

                for (int j = 0; j < words[i].length(); j++) {
                    if (!visited[ words[i].charAt(j) - 'a' ]) {
                        isReadable = false;
                        break;
                    }
                }
                if (isReadable) tmp++;
            }
            ans = Math.max(ans, tmp);
        }

        for (int i = idx; i < 26; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            dfs(i, depth + 1);
            visited[i] = false;
        }
    }
}

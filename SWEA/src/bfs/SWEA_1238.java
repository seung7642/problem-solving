package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_1238 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int T, maxDepth;
    private static List<Integer>[] list;
    private static int[] depthArr;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        while (T++ < 10) {
            depthArr = new int[101];
            list = new List[101];
            for (int i = 0; i <= 100; i++) {
                list[i] = new ArrayList<>();
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            int len = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < len / 2; i++) {
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                list[v1].add(v2);
            }

            maxDepth = 0;
            bfs(start);
            int ans = 0;
            for (int i = 100; i > 0; i--) {
                if (depthArr[i] == maxDepth) {
                    ans = i;
                    break;
                }
            }
            sb.append("#").append(T).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        depthArr[start] = 1;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int item : list[cur]) {
                if (depthArr[item] != 0) continue;
                depthArr[item] = depthArr[cur] + 1;
                maxDepth = Math.max(maxDepth, depthArr[item]);
                q.add(item);
            }
        }
    }
}

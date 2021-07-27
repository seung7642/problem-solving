package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1219 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int T;
    private static List<Integer>[] list;
    private static boolean isExist;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (T++ < 10) {
            list = new ArrayList[100];
            for (int i = 0; i < 100; i++) {
                list[i] = new ArrayList<>();
            }

            int pathLength = Integer.parseInt(br.readLine().split(" ")[1]);
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < pathLength; i++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                list[a].add(b);
            }

            isExist = false;
            dfs(0);
            sb.append("#").append(T).append(" ").append(isExist ? 1 : 0).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int node) {
        if (node == 99) {
            isExist = true;
            return;
        }
        for (int i = 0; i < list[node].size(); i++) {
            dfs(list[node].get(i));
        }
    }
}

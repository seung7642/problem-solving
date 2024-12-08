package graph.minimumSpanningTree;

import java.io.*;
import java.util.StringTokenizer;

// 문제: 최소 비용 신장 트리(Minimum Spanning Tree, MST)
public class BOJ_9372 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int T;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            for (int j = 0; j < E; j++) {
                br.readLine();
            }

            bw.write(V - 1 + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}

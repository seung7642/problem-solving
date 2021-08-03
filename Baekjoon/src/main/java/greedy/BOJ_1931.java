package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1931 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static Node[] nodes;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        nodes = new Node[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            nodes[i] = new Node(start, end);
        }


        Arrays.sort(nodes);

        int cnt = 0, prevEndTime = 0;
        for (int i = 0; i < N; i++) {
            Node cur = nodes[i];
            if (prevEndTime <= cur.start) {
                prevEndTime = cur.end;
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    private static class Node implements Comparable<Node> {
        int start, end;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Node o) {
            return this.end == o.end ? this.start - o.start : this.end - o.end;
        }
    }
}

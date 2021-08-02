package graph.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16953 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static long A, B, ans = -1;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        bfs(A);
        System.out.println(ans);
    }

    private static void bfs(long num) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(num, 1));

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.num == B) {
                ans = cur.cnt;
                break;
            }

            if (cur.num * 2 <= B) {
                q.add(new Node(cur.num * 2, cur.cnt + 1));
            }

            if (cur.num * 10 + 1 <= B) {
                q.add(new Node(cur.num * 10 + 1, cur.cnt + 1));
            }
        }
    }

    private static class Node {
        long num, cnt;

        public Node(long num, long cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}

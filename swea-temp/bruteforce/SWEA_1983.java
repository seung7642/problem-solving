package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1983 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N, K;
    private static String[] grades = {"A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0", "C-", "D0"};
    private static int[] rate = {35, 45, 20};
    private static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            Node[] students = new Node[N];
            for (int i = 0; i < N; i++) {
                int score = 0;
                st = new StringTokenizer(in.readLine());
                for (int j = 0; j < 3; j++) {
                    score += Integer.parseInt(st.nextToken()) * rate[j];
                }
                students[i] = new Node(i + 1, score);
            }

            Arrays.sort(students);
            String grade = "";
            int interval = N / 10, cnt = 0, gradeIdx = 0;
            for (int i = 0; i < N; i++) {
                cnt++;
                if (students[i].idx == K) {
                    grade = grades[gradeIdx];
                    break;
                }
                if (interval == cnt) {
                    cnt = 0;
                    gradeIdx++;
                }
            }
            sb.append("#").append(tc).append(" ").append(grade).append("\n");
        }
        System.out.println(sb);
    }

    private static class Node implements Comparable<Node> {
        int idx, score;

        public Node(int idx, int score) {
            this.idx = idx;
            this.score = score;
        }

        @Override
        public int compareTo(Node o) {
            return o.score - this.score;
        }
    }
}

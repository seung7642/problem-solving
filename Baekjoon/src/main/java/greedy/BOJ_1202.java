package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1202 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, K;
    private static Node[] jewel;
    private static int[] bag;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 보석의 갯수
        K = Integer.parseInt(st.nextToken()); // 가방의 갯수
        jewel = new Node[N];
        bag = new int[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            jewel[i] =  new Node(weight, price);
        }

        for (int i = 0; i < K; i++) {
            bag[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(jewel, (a, b) -> a.weight - b.weight);
        Arrays.sort(bag);

        Queue<Integer> q = new PriorityQueue<>();
        long ans = 0;
        int j = 0;
        for (int i = 0; i < K; i++) {
            while (j < N && jewel[j].weight <= bag[i]) {
                q.add(-jewel[j].price); // 내림차순 정렬로 만드는 기법으로 음수 값을 넣는다.
                j++;
            }
            if (!q.isEmpty()) {
                ans += Math.abs(q.poll());
            }
        }

        System.out.println(ans);
    }

    private static class Node implements Comparable<Node> {
        int weight, price;

        public Node(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
}

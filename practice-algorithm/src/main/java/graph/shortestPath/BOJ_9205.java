package graph.shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_9205 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N;
    private static List<Node> list;
    private static int[][] arr;

    private static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());

            list = new ArrayList<>();
            arr = new int[N + 2][N + 2];
            for (int i = 0; i < N + 2; i++)
                Arrays.fill(arr[i], 999999);

            for (int i = 0; i < N + 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                list.add(new Node(x, y));
            }

            for (int i = 0; i < N + 2; i++) {
                for (int j = 0; j < N + 2; j++) {
                    if (i == j) continue;

                    Node current = list.get(i);
                    Node next = list.get(j);

                    int distance = Math.abs(current.x - next.x) + Math.abs(current.y - next.y);
                    if (distance <= 1000) arr[i][j] = 1;
                }
            }

            floydWarshall();
            if (0 < arr[0][N + 1] && arr[0][N + 1] < 999999)
                System.out.println("happy");
            else
                System.out.println("sad");
        }

        br.close();
    }

    private static void floydWarshall() {
        for (int k = 0; k < N + 2; k++) { // 거쳐가는 정점
            for (int i = 0; i < N + 2; i++) { // 출발지 정점
                for (int j = 0; j < N + 2; j++) { // 목적지 정점
                    if (arr[i][j] > arr[i][k] + arr[k][j])
                        arr[i][j] = arr[i][k] + arr[k][j];
                }
            }
        }
    }
}

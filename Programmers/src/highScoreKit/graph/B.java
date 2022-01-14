package highScoreKit.graph;

public class B {

    public static void main(String[] args) {
        int n;
        int[][] results;

        n = 5;
        results = new int[][]{{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
        System.out.println(solution(n, results)); // 답: 2
    }

    public static int solution(int n, int[][] results) {
        int answer = 0;

        int[][] floyd = new int[n + 1][n + 1];

        for (int i = 1; i < floyd.length; i++) {
            for (int j = 1; j < floyd.length; j++) {
                floyd[i][j] = 10000;
            }
        }

        for (int i = 0; i < results.length; i++) {
            int v1 = results[i][0];
            int v2 = results[i][1];
            floyd[v1][v2] = 1;
        }

        floyd(floyd);

        for (int i = 1; i < floyd.length; i++) {
            int cnt = 0;
            for (int j = 1; j < floyd.length; j++) {
                if (floyd[i][j] < 10000 || floyd[j][i] < 10000) {
                    cnt++;
                }
            }
            if (cnt == n - 1) {
                answer++;
            }
        }

        return answer;
    }

    private static void floyd(int[][] floyd) {
        for (int k = 1; k < floyd.length; k++) { // 거쳐가는 정점
            for (int i = 1; i < floyd.length; i++) { // 출발지 정점
                for (int j = 1; j < floyd.length; j++) { // 목적지 정점
                    if (floyd[i][j] > floyd[i][k] + floyd[k][j]) {
                        floyd[i][j] = floyd[i][k] + floyd[k][j];
                    }
                }
            }
        }
    }
}

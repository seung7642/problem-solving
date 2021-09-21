package blindRecruitment_2021;

// 합승 택시 요금 - 최단경로(플로이드 와샬)
public class D {

    public static void main(String[] args) {
        int n = 6, s = 4, a = 6, b = 2;
        int[][] fares = {{4, 1, 10}
                , {3, 5, 24}
                , {5, 6, 2}
                , {3, 1, 41}
                , {5, 1, 24}
                , {4, 6, 50}
                , {2, 4, 66}
                , {2, 3, 22}
                , {1, 6, 25}};
        int result = solution(n, s, a, b, fares);
        System.out.println(result);
    }

    public static int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        int[][] node = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                node[i][j] = 20_000_001;
            }
            node[i][i] = 0;
        }
        for (int i = 0; i < fares.length; i++) {
            node[fares[i][0]][fares[i][1]] = fares[i][2];
            node[fares[i][1]][fares[i][0]] = fares[i][2];
        }
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (node[i][j] > node[i][k] + node[k][j]) {
                        node[i][j] = node[i][k] + node[k][j];
                    }
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            answer = Math.min(answer, node[s][i] + node[i][a] + node[i][b]);
        }
        return answer;
    }
}

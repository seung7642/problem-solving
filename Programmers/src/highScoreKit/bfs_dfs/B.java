package highScoreKit.bfs_dfs;

public class B {

    public static void main(String[] args) {
        int n;
        int[][] computers;

        computers = new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        n = 3;
        System.out.println(solution(n, computers)); // 답: 2

        computers = new int[][]{{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
        n = 3;
        System.out.println(solution(n, computers)); // 답: 1
    }

    private static boolean[] visited;

    public static int solution(int n, int[][] computers) {
        int cnt = 0;
        visited = new boolean[n];
        for (int i = 0; i < computers.length; i++) {
            if (!visited[i]) {
                cnt++;
                dfs(computers, i);
            }
        }
        return cnt;
    }

    private static void dfs(int[][] computers, int curComputer) {
        for (int i = 0; i < computers[curComputer].length; i++) {
            if (curComputer != i && computers[curComputer][i] == 1 && !visited[i]) {
                visited[i] = true;
                dfs(computers, i);
            }
        }
    }

}

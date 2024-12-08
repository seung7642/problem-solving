package highScoreKit.bfs_dfs;

import java.util.*;

public class D {

    public static void main(String[] args) {
        String[][] tickets;

        tickets = new String[][]{{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        System.out.println(Arrays.toString(solution(tickets))); // 답: ["ICN", "JFK", "HND", "IAD"]

        tickets = new String[][]{{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
        System.out.println(Arrays.toString(solution(tickets))); // 답: ["ICN", "ATL", "ICN", "SFO", "ATL", "SFO"]
    }

    private static boolean[] visited;
    private static List<String> answers;

    public static String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        answers = new ArrayList<>();
        dfs(0, "ICN", "ICN", tickets);
        Collections.sort(answers);

        return answers.get(0).split(" ");
    }

    private static void dfs(int depth, String cur, String answer, String[][] tickets) {
        if (depth == tickets.length) {
            answers.add(answer);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && tickets[i][0].equals(cur)) {
                visited[i] = true;
                dfs(depth + 1, tickets[i][1], answer + " " + tickets[i][1], tickets);
                visited[i] = false;
            }
        }
    }
}

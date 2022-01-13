package highScoreKit.bfs_dfs;

public class C {

    public static void main(String[] args) {
        String begin;
        String target;
        String[] words;

        begin = "hit";
        target = "cog";
        words = new String[]{"hot", "dot", "dog", "lot", "log", "cog"};
        System.out.println(solution(begin, target, words)); // 답: 4

        begin = "hit";
        target = "cog";
        words = new String[]{"hot", "dot", "dog", "lot", "log"};
        System.out.println(solution(begin, target, words)); // 답: 0
    }

    private static boolean[] visited;
    private static int answer = 0;

    public static int solution(String begin, String target, String[] words) {
        answer = Integer.MAX_VALUE;
        visited = new boolean[words.length];
        dfs(begin, target, words, 0);

        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    private static void dfs(String begin, String target, String[] words, int depth) {
        if (begin.equals(target)) {
            answer = Math.min(answer, depth);
            return;
        }

        for (int i = 0; i < words.length; i++) {
            if (visited[i]) continue;
            int cnt = 0;
            for (int j = 0; j < begin.length(); j++) {
                if (begin.charAt(j) == words[i].charAt(j)) {
                    cnt++;
                }
            }

            if (cnt == begin.length() - 1) {
                visited[i] = true;
                dfs(words[i], target, words, depth + 1);
                visited[i] = false;
            }
        }
    }
}

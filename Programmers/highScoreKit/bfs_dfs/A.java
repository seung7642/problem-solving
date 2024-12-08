package highScoreKit.bfs_dfs;

public class A {

    public static void main(String[] args) {
        int[] numbers;
        int target;

        numbers = new int[]{1, 1, 1, 1, 1};
        target = 3;
        System.out.println(solution(numbers, target)); // 답: 5
    }

    private static int answer;

    public static int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);
        return answer;
    }

    private static void dfs(int[] numbers, int target, int depth, int result) {
        if (depth == numbers.length) {
            if (target == result) {
                answer++;
            }
            return;
        }
        int add = result + numbers[depth];
        int sub = result - numbers[depth];
        dfs(numbers, target, depth + 1, add);
        dfs(numbers, target, depth + 1, sub);
    }
}

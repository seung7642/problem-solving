package highScoreKit.dp;

public class A {

    public static void main(String[] args) {
        int N;
        int number;

        N = 5;
        number = 12;
        System.out.println(solution(N, number)); // 답: 4

        N = 2;
        number = 11;
        System.out.println(solution(N, number)); // 답: 3

        N = 5;
        number = 31168;
        System.out.println(solution(N, number)); // 답: -1
    }

    private static int answer;

    public static int solution(int N, int number) {
        answer = 9;
        dfs(0, 0, N, number);
        return answer == 9 ? -1 : answer;
    }

    private static void dfs(int count, int prev, int N, int number) {
        if (prev == number) {
            answer = Math.min(answer, count);
            return;
        }

        // 더하기만을 예로 들어, 만약 prev로 받은 값이 5 라면,
        // 5 + 5
        // 5 + 55
        // 5 + 555
        // 이런 식으로 5가 총 8개될 때 까지 반복합니다.
        int tempN = N;
        for (int i = 0; i < 8 - count; i++) {
            int newCount = count + (i + 1);
            dfs(newCount, prev + tempN, N, number);
            dfs(newCount, prev - tempN, N, number);
            dfs(newCount, prev * tempN, N, number);
            dfs(newCount, prev / tempN, N, number);
            tempN = (tempN * 10) + N;
        }
    }
}

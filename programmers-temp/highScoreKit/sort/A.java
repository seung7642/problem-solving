package highScoreKit.sort;

import java.util.Arrays;

public class A {

    public static void main(String[] args) {
        int[] array;
        int[][] commands;

        array = new int[]{1, 5, 2, 6, 3, 7, 4};
        commands = new int[][]{{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
        System.out.println(Arrays.toString(solution(array, commands))); // 답: [5, 6, 3]
    }

    public static int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int idx = 0;

        for (int[] command : commands) {
            int len = command[1] - command[0] + 1;
            int[] tmp = new int[len];
            System.arraycopy(array, command[0] - 1, tmp, 0, len);
            Arrays.sort(tmp);
            answer[idx++] = tmp[command[2] - 1];
        }

        return answer;
    }
}

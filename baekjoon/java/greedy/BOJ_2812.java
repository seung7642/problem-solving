package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_2812 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int N, K;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        String input = in.readLine();
        char[] arr = input.toCharArray();
        Deque<Character> dq = new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++) {
            while (K > 0 && !dq.isEmpty() && dq.getLast() < arr[i]) {
                dq.removeLast();
                K--;
            }
            dq.addLast(arr[i]);
        }

        while (dq.size() > K) {
            sb.append(dq.removeFirst());
        }

        System.out.println(sb);
    }
}

package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2531 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, d, k, c;
    private static int[] arr;
    private static int[] eat;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        eat = new int[d + 1];
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        System.out.println( twoPointers() );

        br.close();
    }

    private static int twoPointers() {
        int count = 0, max;

        for (int i = 0; i < k; i++) {
            if (eat[ arr[i] ] == 0) count++; // 처음 먹어보는 종류의 초밥이라면 카운트 + 1
            eat[ arr[i] ]++;
        }
        max = count;

        for (int i = 1; i < N; i++) {
            if (max <= count) {
                if (eat[ c ] == 0) max = count + 1;
                else max = count;
            }

            eat[ arr[i - 1] ]--;
            if (eat[ arr[i - 1] ] == 0) count--;

            if (eat[ arr[(i + k - 1) % N]] == 0) count++;
            eat[ arr[(i + k - 1) % N]]++;
        }

        return max;
    }
}

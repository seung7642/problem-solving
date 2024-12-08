package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_1652 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, ansHorizontal, ansVertical;
    private static List<List<Integer>> map;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        map = new ArrayList<>();
        for (int i = 0; i < N; i++)
            map.add(new ArrayList<>());

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = str.charAt(j);
                if (c == '.') map.get(i).add(0);
                else if (c == 'X') map.get(i).add(1);
            }
        }

        for (int i = 0; i < N; i++) { // 가로
            int cnt = 0;
            for (int j = 0; j < N; j++) {
                if (map.get(i).get(j) == 0) cnt++;
                else {
                    if (cnt >= 2) ansHorizontal++;
                    cnt = 0;
                }
                if (j == N - 1)
                    if (cnt >= 2) ansHorizontal++;
            }
        }

        for (int i = 0; i < N; i++) { // 세로
            int cnt = 0;
            for (int j = 0; j < N; j++) {
                if (map.get(j).get(i) == 0) cnt++;
                else {
                    if (cnt >= 2) ansVertical++;
                    cnt = 0;
                }
                if (j == N - 1)
                    if (cnt >= 2) ansVertical++;
            }
        }

        System.out.println(ansHorizontal + " " + ansVertical);

        br.close();
    }
}

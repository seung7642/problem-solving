package string;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_1620 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            Map<Integer, String> map = new HashMap<>();
            Map<String, Integer> rmap = new HashMap<>();
            for (int i = 1; i <= N; i++) {
                String monsterName = br.readLine();

                map.put(i, monsterName);
                rmap.put(monsterName, i);
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < M; i++) {
                String input = br.readLine();
                char c = input.charAt(0);

                if (Character.isDigit(c)) { // 입력이 숫자로 들어온 경우
                    sb.append(map.get(Integer.parseInt(input))).append("\n");
                } else {
                    sb.append(rmap.get(input)).append("\n");
                }
            }

            System.out.println(sb.toString());
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
}

package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_9375 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();

            while (T-- > 0) {
                int N = Integer.parseInt(br.readLine());
                Map<String, Integer> costume = new HashMap<>(); // (의상의 종류, 갯수)

                while (N-- > 0) {
                    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                    st.nextToken(); // 옷의 이름
                    String kind = st.nextToken();

                    if (costume.containsKey(kind)) {
                        costume.put(kind, costume.get(kind) + 1);
                    } else {
                        costume.put(kind, 1);
                    }
                }

                int result = 1;

                // 안 입는 경우도 있으므로, +1을 해준다.
                for (int val : costume.values()) {
                    result *= (val + 1);
                }

                sb.append(result - 1).append('\n');
            }

            System.out.println(sb);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
}

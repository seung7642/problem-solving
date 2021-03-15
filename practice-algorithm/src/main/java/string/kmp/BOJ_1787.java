package string.kmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 접두사(Prefix)와 접미사(Suffix)의 길이가 가장 짧은 것 구하기.
// (원래의 실패 함수라면 접두사와 접미사의 길이가 가장 긴 것을 반환한다.)
public class BOJ_1787 {

    private static final int MAX = 1_000_000;
    private static int[] pi = new int[MAX];
    private static int[] cache = new int[MAX];

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            String str = br.readLine();

            long ans = 0;
            for (int i = 1, j = 0; i < N; i++) {
                while (j > 0 && str.charAt(i) != str.charAt(j)) {
                    j = pi[j - 1];
                }
                if (str.charAt(i) == str.charAt(j)) {
                    pi[i] = ++j;
                }

                // 인덱스는 0번부터 시작하기 때문에 -1 을 해준다.
                if (pi[i] - 1 > 0) {
                    cache[i] = (pi[pi[i] - 1] > 0 ? cache[pi[i] - 1] : pi[i]);
                } else {
                    cache[i] = pi[i];
                }

                if (cache[i] == 0) continue;
                if (i + 1 - cache[i] >= cache[i]) {
                    ans += i + 1 - cache[i];
                }
            }

            System.out.println(ans);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
}

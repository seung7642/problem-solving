package blindRecruitment_2020;

public class A {

    public static void main(String[] args) {
        String s;
        int result;

        s = "aabbaccc";
        result = solution(s);
        System.out.println(result); // 답: 7

        s = "ababcdcdababcdcd";
        result = solution(s);
        System.out.println(result); // 답: 9

        s = "abcabcdede";
        result = solution(s);
        System.out.println(result); // 답: 8
    }

    private static int N;

    public static int solution(String s) {
        N = s.length();
        int ans = s.length();
        for (int i = 1; i <= N / 2; i++) {
            ans = Math.min(ans, compress(s, i));
        }
        return ans;
    }

    private static int compress(String s, int k) {
        StringBuffer sb = new StringBuffer();
        String prev = s.substring(0, k), cur = "";
        int cnt = 1;
        for (int i = k; i < N; i += k) {
            if (i + k < N) {
                cur = s.substring(i, i + k);
            } else if (i + k >= N) {
                cur = s.substring(i);
                if (prev.equals(cur)) {
                    sb.append(++cnt).append(prev);
                } else {
                    if (cnt >= 2) sb.append(cnt);
                    sb.append(prev).append(cur);
                }
                break;
            }

            if (prev.equals(cur)) {
                cnt++;
            } else {
                if (cnt >= 2) sb.append(cnt);
                sb.append(prev);
                prev = cur;
                cnt = 1;
            }
        }
        return sb.length();
    }
}

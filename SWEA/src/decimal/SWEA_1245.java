package decimal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 균형점
public class SWEA_1245 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int T, N;
    static int[] X, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            X = new int[N]; // 자성체들의 좌표 값
            M = new int[N]; // 자성체들의 질량 값
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) { X[i] = Integer.parseInt(st.nextToken()); }
            for (int i = 0; i < N; i++) { M[i] = Integer.parseInt(st.nextToken()); }

            System.out.printf("#%d", tc);
            for (int i = 0; i < N - 1; i++) {
                System.out.printf(" %.10f", solve(i,0,(X[i] + X[i + 1]) / 2, X[i], X[i + 1]));
            }

            System.out.println();
        }
    }

    static double solve(int idx, int depth, double mid, double left, double right) {
        if (depth == 100) return mid; // 실수형의 오차범위 때문에 100~200번 정도만 반복한다. (double의 오차범위는 1e-15)
        double f = 0.0;
        double val = 0.0;

        // 자성체로부터 물체에 작용하는 인력을 구하는 공식: F = G * m1 * m2 / (d * d)
        // d는 자성체와 물체 사이의 거리. m은 자성체와 물체의 질량
        for (int i = 0; i <= idx; i++) { f += (double) M[i] / Math.pow(mid - X[i], 2.0); } // mid를 기준으로 좌측에 작용하는 인력
        for (int i = idx + 1; i < N; i++) { f -= (double) M[i] / Math.pow(X[i] - mid, 2.0); } // mid를 기준으로 우측에 작용하는 인력

        if (f < 0) { val = solve(idx,depth + 1, (left + mid) / 2.0, left, mid); } 
        else if (f > 0) { val = solve(idx,depth + 1, (mid + right) / 2.0, mid, right); } 
        else { val = mid; }
        return val;
    }
}

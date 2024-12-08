package string;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_12813 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] a = new int[100001];
        int[] b = new int[100001];
        String t = br.readLine();
        String tt = br.readLine();

        for (int i = 0; i < t.length(); i++) {
            a[i] = Character.digit(t.charAt(i), 10);
            b[i] = Character.digit(tt.charAt(i), 10);
        }

        StringBuilder[] sb = new StringBuilder[5];
        for (int i = 0; i < 5; i++) {
            sb[i] = new StringBuilder();
        }

        for (int i = 0; i < t.length(); i++) {
            // A & B
            sb[0].append(a[i] & b[i]);

            // A | B
            sb[1].append(a[i] | b[i]);

            // A ^ B
            sb[2].append(a[i] ^ b[i]);

            // ~A
            sb[3].append(a[i] ^ 1);

            // ~B
            sb[4].append(b[i] ^ 1);
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(sb[i].toString());
        }
    }
}

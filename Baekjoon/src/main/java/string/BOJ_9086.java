package string;

import java.io.*;

public class BOJ_9086 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int T;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String str = br.readLine();
            char first = str.charAt(0);
            char last = str.charAt(str.length() - 1);
            System.out.println(first + "" + last);
        }

        bw.flush();
        bw.close();
        br.close();
    }
}

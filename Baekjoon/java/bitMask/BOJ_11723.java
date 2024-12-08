package bitMask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11723 {

    private static int M, bit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        M = Integer.parseInt(br.readLine());

        String cmd;
        int val;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            cmd = st.nextToken();

            if (cmd.equals("add")) {
                val = Integer.parseInt(st.nextToken()) - 1;
                bit |= (1 << val);
            } else if (cmd.equals("remove")) {
                val = Integer.parseInt(st.nextToken()) - 1;
                bit &= ~(1 << val);
            } else if (cmd.equals("check")) {
                val = Integer.parseInt(st.nextToken()) - 1;
                int result = (bit & (1 << val)) == 0 ? 0 : 1;
                sb.append(result).append("\n");
            } else if (cmd.equals("toggle")) {
                val = Integer.parseInt(st.nextToken()) - 1;
                bit ^= (1 << val);
            } else if (cmd.equals("all")) {
                bit |= (~0);
            } else if (cmd.equals("empty")) {
                bit &= 0;
            }
        }

        System.out.println(sb.toString());
    }
}

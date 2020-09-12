package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1373 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String str = br.readLine();

        int size = str.length();
        if (size % 3 == 1) {
            System.out.print(str.charAt(0));
        } else if (size % 3 == 2) {
            System.out.print( ((str.charAt(0) - '0') * 2) + (str.charAt(1) - '0'));
        }

        for (int i = size % 3; i < size; i += 3) {
            System.out.print( ((str.charAt(i) - '0') * 4) +
                    ((str.charAt(i + 1) - '0') * 2) +
                    (str.charAt(i + 2) - '0'));
        }

        br.close();
    }
}

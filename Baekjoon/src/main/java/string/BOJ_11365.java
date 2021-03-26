package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11365 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        while (true) {
            String str = br.readLine();
            if (str.equals("END")) break;

            for (int i = str.length() - 1; i >= 0; i--)
                System.out.print(str.charAt(i));
            System.out.println();
        }

        br.close();
    }
}

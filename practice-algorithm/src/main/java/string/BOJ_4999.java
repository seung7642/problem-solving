package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_4999 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String s1 = br.readLine();
        String s2 = br.readLine();

        if (s1.length() >= s2.length()) System.out.println("go");
        else System.out.println("no");

        br.close();
    }
}

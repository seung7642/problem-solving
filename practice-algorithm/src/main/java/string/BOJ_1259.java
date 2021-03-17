package string;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1259 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String input;
            while (!(input = br.readLine()).equals("0")) {
                int length = input.length();
                boolean isSame = false;
                for (int i = 0, j = length - 1; i < length; i++, j--) {
                    if (i >= j) {
                        isSame = true;
                        break;
                    }

                    if (input.charAt(i) != input.charAt(j)) {
                        break;
                    }
                }

                System.out.println(isSame ? "yes" : "no");
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
}

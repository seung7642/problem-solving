package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3003 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int[] arr1 = new int[6];
    private static int[] arr2 = {1, 1, 2, 2, 2, 8};

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < 6; i++) { arr1[i] = Integer.parseInt(st.nextToken()); }
        
        for (int i = 0; i < 6; i++) {
            if (arr2[i] < arr1[i]) {
                arr1[i] = -(arr1[i] - arr2[i]);
            } else if (arr2[i] > arr1[i]) {
                arr1[i] = (arr2[i] - arr1[i]);
            } else {
                arr1[i] = 0;
            }
            System.out.print(arr1[i] + " ");
        }
        System.out.println();
    }
}

package dynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11660 {
    
    private static int N, M;
    private static int[][] map, cache;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 표의 크기 
        M = Integer.parseInt(st.nextToken()); // 구해야 하는 횟수 
        map = new int[N + 1][N + 1]; // 입력받은 수를 저장할 배열 
        cache = new int[N + 1][N + 1]; // (0,0) 부터 해당 좌표까지의 모든 수의 합을 저장할 cache 배열 

        for (int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1 ; j <= N ; j++) {  
                map[i][j] = Integer.parseInt(st.nextToken());  
                cache[i][j] = cache[i - 1][j] + cache[i][j - 1] - cache [i - 1][j - 1] + map[i][j];
            }
        }

        for (int i = 1 ; i <= M ; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int result = cache[x2][y2] - cache[x2][y1 - 1] - cache[x1 - 1][y2] + cache[x1 - 1][y1 - 1] ;
            sb.append(result).append("\n");
        }
        
        System.out.println(sb.toString());
    }
}
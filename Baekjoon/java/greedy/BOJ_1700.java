package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1700 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, K, ans;
    private static int[] arr;
    private static boolean[] used;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 멀티탭 구멍의 수
        K = Integer.parseInt(st.nextToken()); // 전기용품 사용횟수

        used = new boolean[K + 1];
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int idx = 0, cnt = 0;
        while (true) {
            if (cnt == N) break;
            if (!used[arr[idx]]) {
                used[arr[idx]] = true;
                cnt++;
            }
            idx++;
        }

        // 1. 다음에 사용되는 전기용품이 이미 콘센트에 꽃혀 있다면 패스.
        // 2. 다음에 사용되는 전기용품이 콘센트에 꽃혀있지 않은 경우
        //   2-1. 사용할 전기용품의 인덱스부터 마지막까지 봤을 때 현재 꽃혀있는 것과 중복된 경우
        //        앞으로 가장 적게 사용되는 전기용품의 콘센트를 빼고 현재 사용할 전기용품의 콘센트를 꼽는다.
        //   2-2. 사용할 전기용품의 인덱스부터 멀티탭 구멍의 갯수(N)만큼을 봤을 때 현재 꽃혀있는 것과 중복되지 않을 경우
        //

        while (idx < K) {
            // 지금 사용할 전기용품의 콘센트가 멀티탭에 꽃혀있지 않은 경우
            if (!used[arr[idx]]) {
                List<Integer> list = new ArrayList<>();
                for (int i = idx; i < K; i++) {
                    if (used[arr[i]] && !list.contains(arr[i])) {
                        list.add(arr[i]);
                    }
                }

                if (list.size() == N) {
                    int remove = list.get(list.size() - 1);
                    used[remove] = false;
                } else {
                    for (int j = 1; j <= K; j++) {
                        if (used[j] && !list.contains(j)) {
                            used[j] = false;
                            break;
                        }
                    }
                }

                used[arr[idx]] = true;
                ans++;
            }

            idx++;
        }

        System.out.println(ans);
    }
}

package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_14476 {

    private static final int  MAX_N = 1_000_000;
    private static long[] segmentTree = new long[MAX_N * 4];
    private static long[] arr;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            arr = Arrays.stream(br.readLine().split(" "))
                    .mapToLong(Long::parseLong)
                    .toArray();

            int firstLeafNodeIdx = getFirstLeafNodeIdx(N);
            init(firstLeafNodeIdx, N);
            solve(firstLeafNodeIdx, N);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    private static void solve(int firstIdx, int n) {
        long maxGcd = 0;    // 가장 큰 최대공약수
        long removeVal = 0; // 뺀 수
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            long curNum = arr[i];

            // 현재 숫자를 제외시킨다. (값을 0으로 갱신)
            updateSegmentTree(firstIdx, i, 0);

            // 나머지들의 최대공약수가 뺀 수의 약수인지 판별한다.
            if (arr[i] % segmentTree[1] != 0 && maxGcd < segmentTree[1]) {
                maxGcd = segmentTree[1];
                removeVal = arr[i];
            }

            if (arr[i] % segmentTree[1] == 0) {
                cnt++;
            }

            // 원래 상태로 복구.
            updateSegmentTree(firstIdx, i, curNum);
        }

        if (cnt == n) {
            System.out.println("-1");
        } else {
            System.out.println(maxGcd + " " + removeVal);
        }
    }

    private static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // 배열에서 리프 노드가 저장될 첫 번째 인덱스를 반환한다.
    private static int getFirstLeafNodeIdx(long n) {
        int ret = 1;
        while (ret < n) {
            ret *= 2;
        }
        return ret;
    }

    // i~j 구간의 최대공약수를 구한다.
    private static void init(int firstLeafNodeIdx, int n) {
        // 리프 노드 (원소를 저장)
        for (int i = firstLeafNodeIdx; i < firstLeafNodeIdx + n; i++) {
            segmentTree[i] = arr[i - firstLeafNodeIdx];
        }

        // 리프 노드가 아닌 노드 (구간에 대한 연산값을 저장)
        for (int i = firstLeafNodeIdx - 1; i >= 1; i--) {
            segmentTree[i] = gcd(segmentTree[i * 2], segmentTree[i * 2 + 1]);
        }
    }

    // updateIdx: 빼고자하는 수의 인덱스
    private static void updateSegmentTree(int firstIdx, int updateIdx, long newValue) {
        int tmpIdx = firstIdx + updateIdx;
        segmentTree[tmpIdx] = newValue;
        while (tmpIdx >= 1) {
            tmpIdx /= 2;
            segmentTree[tmpIdx] = gcd(segmentTree[tmpIdx * 2], segmentTree[tmpIdx * 2 + 1]);
        }
    }
}

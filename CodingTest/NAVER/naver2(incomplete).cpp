#include <string>
#include <vector>
#include <algorithm>
using namespace std;

constexpr int MAX = 1001;

long long dp[MAX][MAX];
long long res[MAX * MAX];

long long solution(int n) {
    long long answer = 0;

    for (int i = 1; i <= 1000; i++)
        dp[i][i] = i;

    for (int i = 1; i < 1000; i++)
        dp[i][i + 1] = i * (i + 1);

    for (int i = 1, len = 2; i <= 1000 - 2; i++, len++) {
        for (int j = 1; j <= 1000; j++) {
            dp[j][j + len] = dp[j][j + len - 1] * (j + len);
        }
    }

    for (int i = 1, len = 1; i <= 1000 * 1000; i++, len++) {
        res[i] = 100000000000;

    }
    long long cnt = 0;
    for (int i = 1, len = 1; i <= 1000; i++, len++) {
        for (int j = 1; j <= 1000; j++) {
            res[cnt++] = dp[j][j + len];
        }
    }

    answer = res[n - 1];

    return answer;
}
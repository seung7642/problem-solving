#include <bits/stdc++.h>
using namespace std;

constexpr int contest2017[] = { 0, 5000000, 3000000, 2000000, 500000, 300000, 100000 };
constexpr int contest2018[] = { 0, 5120000, 2560000, 1280000, 640000, 320000 };
int T;

int sum2017(int n) {
    return (n * (n + 1)) / 2;
}

int sum2018(int n) {
    return (1 * (pow(2, n) - 1)) / (2 - 1); // r = 2인 등비수열의 합 공식
}

int main() {
    cin >> T;

    int a, b;
    while (T--) {
        cin >> a >> b;

        int result = 0;
        for (int i = 1; i <= 6; ++i) {
            if (a != 0 && a <= sum2017(i)) {
                result += contest2017[i];
                break;
            }
        }

        for (int i = 1; i <= 5; ++i) {
            if (b != 0 && b <= sum2018(i)) {
                result += contest2018[i];
                break;
            }
        }

        cout << result << "\n";
    }

    return 0;
}
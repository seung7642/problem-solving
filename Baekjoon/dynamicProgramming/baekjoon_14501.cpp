#include <iostream>
using namespace std;

int N;
int ans;
int a[16];
int b[16];

void go(int index, int sum) {
    if (index == N + 1) {
        if (sum > ans) ans = sum;
        return;
    }

    if (index > N + 1) return;
    go(index + a[index], sum + b[index]);
    go(index + 1, sum);
}

int main() {
    cin >> N;

    for (int i = 1; i <= N; i++) {
        cin >> a[i] >> b[i];
    }

    go(1, 0);
    cout << ans << "\n";

    return 0;
}
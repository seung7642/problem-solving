#include <iostream>
using namespace std;

int N;
bool map[101][101];
int ans;

int main() {
    cin >> N;

    for (int i = 0, left, bottom; i < N; i++) {
        cin >> left >> bottom;

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                map[y + bottom][x + left] = true;
            }
        }
    }

    for (int y = 0; y < 100; y++) {
        for (int x = 0; x < 100; x++) {
            if (map[y][x]) ans++;
        }
    }

    cout << ans << "\n";

    return 0;
}
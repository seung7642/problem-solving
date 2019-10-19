#include <iostream>
using namespace std;

int A, B, C;
int arr[101];
int result;

int main() {
    cin >> A >> B >> C;

    for (int i = 0, a, b; i < 3; i++) {
        cin >> a >> b;

        for (int j = a; j < b; j++) {
            arr[j]++;
        }
    }

    for (int i = 1; i <= 100; i++) {
        if (arr[i] == 1) result += 1 * A;
        else if (arr[i] == 2) result += 2 * B;
        else if (arr[i] == 3) result += 3 * C;
    }

    cout << result << "\n";

    return 0;
}
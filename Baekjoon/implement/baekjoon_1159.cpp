#include <iostream>
#include <string>
using namespace std;

int N;
int alpha[26];
string str;
int cnt;

int main() {
    cin >> N;

    while (N--) {
        cin >> str;
        alpha[str[0] - 'a']++;
    }

    for (int i = 0; i < 26; i++) {
        char a;
        if (alpha[i] >= 5) {
            a = 'a' + i;
            cout << a;
            cnt++;
        }
    }

    if (!cnt) cout << "PREDAJA\n";

    return 0;
}
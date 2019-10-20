#include <iostream>
#include <string>
using namespace std;

int alpha[26];
string str;
int maxAlpha;

int main() {
    while (getline(cin, str)) {
        for (int i = 0; i < str.length(); i++) {
            alpha[str[i] - 'a']++;
        }
    }

    for (int i = 0; i < 26; i++) {
        if (maxAlpha < alpha[i]) maxAlpha = alpha[i];
    }

    for (int i = 0; i < 26; i++) {
        if (maxAlpha == alpha[i]) cout << (char)(i + 'a');
    }

    return 0;
}
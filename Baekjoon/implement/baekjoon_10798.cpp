#include <iostream>
using namespace std;

char input[5][15] = { 0, };

int main() {
    for (int i = 0; i < 5; i++) {
        cin >> input[i];
    }

    for (int x = 0; x < 15; x++) {
        for (int y = 0; y < 5; y++) {
            if (isalpha(input[y][x]) || isdigit(input[y][x])) cout << input[y][x];
            else continue;
        }
    }

    return 0;
}
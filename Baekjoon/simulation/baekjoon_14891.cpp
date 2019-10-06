#include <iostream>
#include <string>
#include <queue>
using namespace std;

bool gear[5][8]; // 0: N극, 1: S극
int K; // 회전 횟수
int ans;

void gearRotation(int gearNumber, int direction) {
    int newGear[8] = { 0, };

    if (direction == 1) { // 시계 방향
        for (int i = 0; i < 8; i++) {
            if (i) newGear[i] = gear[gearNumber][i - 1];
            else newGear[0] = gear[gearNumber][7];
        }
    }
    else if (direction == -1) {
        for (int i = 0; i < 8; i++) {
            newGear[i] = gear[gearNumber][(i + 1) % 8];
        }
    }

    for (int i = 0; i < 8; i++)
        gear[gearNumber][i] = newGear[i];
}

void sumScore(int gearNumber, int n) {
    if (gear[gearNumber][0]) ans += n;
}

int main() {
    for (int i = 1; i <= 4; i++) {
        string str;
        cin >> str;
        for (int j = 0; j < 8; j++)
            gear[i][j] = str[j] - '0';
    }

    cin >> K;
    for (int i = 0; i < K; i++) {
        queue<pair<int, int>> q; // first: 돌려야할 톱니바퀴번호, second: 방향
        int gearNumber, direction;
        cin >> gearNumber >> direction;
        q.push({ gearNumber, direction });

        int tempGearNumber, tempDirection;
        tempGearNumber = gearNumber;
        tempDirection = direction;
        while (tempGearNumber > 1) { // 좌측
            if (gear[tempGearNumber - 1][2] != gear[tempGearNumber][6]) {
                tempDirection *= -1;
                tempGearNumber--;
                q.push({ tempGearNumber, tempDirection });
            }
            else break;
        }

        tempGearNumber = gearNumber;
        tempDirection = direction;
        while (tempGearNumber < 4) { // 우측
            if (gear[tempGearNumber][2] != gear[tempGearNumber + 1][6]) {
                tempDirection *= -1;
                tempGearNumber++;
                q.push({ tempGearNumber, tempDirection });
            }
            else break;
        }

        while (!q.empty()) {
            gearNumber = q.front().first;
            direction = q.front().second;
            gearRotation(gearNumber, direction);
            q.pop();
        }
    }

    for (int i = 0; i < 4; i++) {
        int n = 1 << i;
        sumScore(i + 1, n);
    }

    cout << ans << "\n";

    return 0;
}
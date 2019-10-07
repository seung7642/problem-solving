#include <iostream>
using namespace std;

short map[100][100];
short N, L;
short cnt;

bool checkHorizonLoad(int idx) {
    bool chk[100] = { 0, }; // i번째 칸에 경사로를 설치했다면 true

    for (int i = 0; i < N - 1; i++) {
        if (map[idx][i] != map[idx][i + 1]) {
            short diff = map[idx][i] - map[idx][i + 1];
            if (!(diff == -1 || diff == 1)) return false;

            if (diff == -1) { // i번째 칸을 기준으로 좌측으로 길이가 L인 경사로를 설치할 수 있는지 확인한다.
                for (int j = 0; j < L; j++) {
                    if (i - j < 0 || chk[i - j]) return false;

                    if (map[idx][i] == map[idx][i - j]) chk[i - j] = true;
                    else return false;
                }
            }
            else if (diff == 1) { // i번째 칸을 기준으로 우측으로 길이가 L인 경사로를 설치할 수 있는지 확인한다.
                for (int j = 1; j <= L; j++) {
                    if (i + j >= N || chk[i + j]) return false;

                    if (map[idx][i] - 1 == map[idx][i + j]) chk[i + j] = true;
                    else return false;
                }
            }
        }
    }

    return true;
}

bool checkVerticalLoad(int idx) {
    bool chk[100] = { 0, }; // i번째 칸에 경사로를 설치했다면 true

    for (int i = 0; i < N - 1; i++) {
        if (map[i][idx] != map[i + 1][idx]) {
            short diff = map[i][idx] - map[i + 1][idx];
            if (!(diff == -1 || diff == 1)) return false;

            if (diff == -1) { // i번째 칸을 기준으로 좌측으로 길이가 L인 경사로를 설치할 수 있는지 확인한다.
                for (int j = 0; j < L; j++) {
                    if (i - j < 0 || chk[i - j]) return false;

                    if (map[i][idx] == map[i - j][idx]) chk[i - j] = true;
                    else return false;
                }
            }
            else if (diff == 1) { // i번째 칸을 기준으로 우측으로 길이가 L인 경사로를 설치할 수 있는지 확인한다.
                for (int j = 1; j <= L; j++) {
                    if (i + j >= N || chk[i + j]) return false;

                    if (map[i][idx] - 1 == map[i + j][idx]) chk[i + j] = true;
                    else return false;
                }
            }
        }
    }

    return true;
}

int main() {
    cin >> N >> L;

    for (int y = 0; y < N; y++)
        for (int x = 0; x < N; x++)
            cin >> map[y][x];

    for (int i = 0; i < N; i++) {
        if (checkVerticalLoad(i)) cnt++;
        if (checkHorizonLoad(i)) cnt++;
    }

    cout << cnt << "\n";

    return 0;
}
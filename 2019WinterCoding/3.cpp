// N: 4
// map
//     1 4 8 10
//     5 5 5 5
//     10 10 10 10
//     10 10 10 20
// height: 3
// result: 15

#include <iostream>
#include <queue>
#include <cmath>
using namespace std;

constexpr int directionX[] = { 0, 0, -1, 1 };
constexpr int directionY[] = { -1, 1, 0, 0 };

int map[300][300];
int domainMap[300][300];
bool visited[300][300];
int N;
int height;
int ans;

bool rangeCheck(int x, int y) {
    return 0 <= x && x < N && 0 <= y && y < N;
}

void setDomain(int x, int y, int domain) {
    queue<pair<int, int>> q;
    q.push({ x, y });
    visited[y][x] = true;
    domainMap[y][x] = domain;

    while (!q.empty()) {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();

        for (int i = 0; i < 4; i++) {
            int nextX = x + directionX[i];
            int nextY = y + directionY[i];

            if (rangeCheck(nextX, nextY)) {
                if (!visited[nextY][nextX] && abs(map[y][x] - map[nextY][nextX]) <= 3) {
                    visited[nextY][nextX] = true;
                    domainMap[nextY][nextX] = domain;
                    q.push({ nextX, nextY });
                }
            }
        }
    }
}

vector<int> adjoinDomain(int domain) { // 해당 도메인에 인접한 도메인을 반환
    vector<int> v;

    for (int y = 0; y < N; y++) {
        for (int x = 0; x < N; x++) {
            if (domainMap[y][x] == domain) {
                for (int i = 0; i < 4; i++) {
                    int nextX = x + directionX[i];
                    int nextY = y + directionY[i];

                    if (domainMap[nextY][nextX] != domain) {
                        v.push_back(domainMap[nextY][nextX]);
                    }
                }
            }
        }
    }

    return v;
}

int costAToB(int start, int end) { // start 영역에서 end 영역으로 가는 최소 비용
    int minimunCost = 10000000;

    for (int y = 0; y < N; y++) {
        for (int x = 0; x < N; x++) {
            if (domainMap[y][x] == start) {

                for (int i = 0; i < 4; i++) {
                    int nextX = x + directionX[i];
                    int nextY = y + directionY[i];

                    if (domainMap[nextY][nextX] == end) {
                        int cost = abs(map[y][x] - map[nextY][nextX]);
                        if (minimunCost > cost) minimunCost = cost;
                    }
                }
            }
        }
    }

    return minimunCost;
}

int main() {
    cin >> N;
    for (int y = 0; y < N; y++)
        for (int x = 0; x < N; x++)
            cin >> map[y][x];

        cin >> height;

        int domainCount = 0;
    for (int y = 0; y < N; y++) { // 도메인 설정
        for (int x = 0; x < N; x++) {
            if (!visited[y][x]) {
                setDomain(x, y, ++domainCount);
            }
        }
    }

    while (true) { // domainMap의 모든 영역이 1이 될때까지 반복
        vector<int> v;
        int minimunCost = 10000000;
        int minimunCostDomain = 0;
        v = adjoinDomain(1); // 1번 영역과 인접한 영역을 가져온다.

        for (int i = 0; i < v.size(); i++) { // 1번 영역과 인접한 영역 중에서 최소 비용을 가지는 영역을 찾는다.
            int cost = costAToB(1, v[i]);
            if (minimunCost > cost) {
                minimunCost = cost;
                minimunCostDomain = v[i];
            }
        }

        ans += minimunCost;
        for (int y = 0; y < N; y++) { // 1번 영역과 인접한 영역 중 최소 비용을 가지는 영역을 1번 영역으로 초기화
            for (int x = 0; x < N; x++) {
                if (domainMap[y][x] = minimunCostDomain)
                    domainMap[y][x] = 1; // 1번 영역으로 바꿈
            }
        }

        int flag = true;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (domainMap[y][x] != 1) flag = false;
            }
        }

        if (flag) break;
    }

    cout << ans << "\n";

    return 0;
}
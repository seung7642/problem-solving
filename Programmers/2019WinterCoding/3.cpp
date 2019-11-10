// 입력 예)
// N : 4
// map = [
//     [1 4 8 10],
//     [5 5 5 5],
//     [10 10 10 10],
//     [10 10 10 20]]
// height : 3
// result : 15
#include <bits/stdc++.h>
using namespace std;

constexpr int directionX[] = { 0, 0, -1, 1 };
constexpr int directionY[] = { -1, 1, 0, 0 };

int map[300][300];
int domainMap[300][300];
bool visited[300][300];
int N;
int height;
int ans;

void init() {
    cin >> N;
    for (int y = 0; y < N; y++)
        for (int x = 0; x < N; x++)
            cin >> map[y][x];

    cin >> height;
}

bool rangeCheck(int x, int y) {
    return 0 <= x && x < N && 0 <= y && y < N;
}

void bfs(int x, int y, int domain) {
    queue<pair<int, int>> q;
    q.push({ x, y });
    visited[y][x] = true;
    domainMap[y][x] = domain;

    while (!q.empty()) {
        auto [x, y] = q.front(); // C++17
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

void setDomain() { // domainMap에 도메인 설정
    int domainCount = 0;
    for (int y = 0; y < N; y++) {
        for (int x = 0; x < N; x++) {
            if (!visited[y][x]) {
                bfs(x, y, ++domainCount);
            }
        }
    }
}

vector<int> getAdjoinDomain(int domain) { // 해당 도메인에 인접한 도메인을 반환
    vector<int> v;

    for (int y = 0; y < N; y++) {
        for (int x = 0; x < N; x++) {
            if (domainMap[y][x] == domain) {
                for (int i = 0; i < 4; i++) {
                    int nextX = x + directionX[i];
                    int nextY = y + directionY[i];

                    if (rangeCheck(nextX, nextY) && domainMap[nextY][nextX] != domain) {
                        v.push_back(domainMap[nextY][nextX]);
                    }
                }
            }
        }
    }

    return v;
}

int getCost(int startDomain, int endDomain) { // start 도메인에서 end 도메인으로 가는 최소 비용
    int minimumCost = 10000000;

    for (int y = 0; y < N; y++) {
        for (int x = 0; x < N; x++) {
            if (domainMap[y][x] == startDomain) {
                for (int i = 0; i < 4; i++) {
                    int nextX = x + directionX[i];
                    int nextY = y + directionY[i];

                    if (rangeCheck(nextX, nextY) && domainMap[nextY][nextX] == endDomain) {
                        int cost = abs(map[y][x] - map[nextY][nextX]);
                        if (minimumCost > cost) minimumCost = cost;
                    }
                }
            }
        }
    }

    return minimumCost;
}

void changeDomain(int startDomain, int endDomain) { // startDomain -> endDomain으로 도메인 초기화
    for (int y = 0; y < N; y++) {
        for (int x = 0; x < N; x++) {
            if (domainMap[y][x] == startDomain)
                domainMap[y][x] = endDomain;
        }
    }
}

bool isUnify(int domain) {
    for (int y = 0; y < N; y++) {
        for (int x = 0; x < N; x++) {
            if (domainMap[y][x] != domain) return false;
        }
    }
    return true;
}

int main() {
    init();
    setDomain();

    while (true) { // domainMap의 모든 영역이 1이 될때까지 반복
        vector<int> v;
        int minimumCost = 10000000;
        int minimumCostDomain = 0;
        v = getAdjoinDomain(1); // 1번 영역과 인접한 영역을 가져온다.

        for (int i = 0; i < v.size(); i++) { // 1번 영역과 인접한 영역 중에서 최소 비용을 가지는 영역을 찾는다.
            int cost = getCost(1, v[i]);
            if (minimumCost > cost) {
                minimumCost = cost;
                minimumCostDomain = v[i];
            }
        }

        ans += minimumCost;

        changeDomain(minimumCostDomain, 1); // 1번 영역과 인접한 영역 중 최소 비용을 가지는 영역을 1번 영역으로 초기화

        if (isUnify(1)) break; // 모든 도메인이 1번으로 통일되었다면 루프 종료.
    }

    cout << ans << "\n";
    return 0;
}
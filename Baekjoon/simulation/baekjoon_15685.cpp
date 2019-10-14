#include <iostream>
#include <vector>
using namespace std;

constexpr int MAX = 100 + 2;

int N;
bool is_visited[MAX][MAX];
int directionX[4] = { 1, 0, -1, 0 };
int directionY[4] = { 0, -1, 0, 1 };

void make_dragon_curve(vector<pair<int, int>>& _dragon_curve, int generation) {
    while (generation--) {
        // 가장 마지막 점이 회전의 기준
        pair<int, int> pivot = _dragon_curve.back();

        int dragon_size = _dragon_curve.size();
        for (int idx = dragon_size - 2; idx >= 0; idx--) {
            // 원점이동 -> 회전이동 -> 다시 이동 후에 삽입한다.
            pair<int, int> new_pivot = {
                _dragon_curve[idx].first - pivot.first,
                _dragon_curve[idx].second - pivot.second };
            _dragon_curve.push_back({ -new_pivot.second + pivot.first, new_pivot.first + pivot.second });
        }
    }
}

int main() {
    cin >> N;
    for (int n_idx = 0; n_idx < N; n_idx++) {
        int x, y, d, g;
        cin >> x >> y >> d >> g;
        // 드래곤 커브를 만든다. 벡터로 표현한다
        vector<pair<int, int>> dragon_curve;
        dragon_curve.push_back({ x, y });
        dragon_curve.push_back({ x + directionX[d], y + directionY[d] });

        make_dragon_curve(dragon_curve, g);

        // 드래곤 커브가 도달할 수 있는 배열에 전부 표시한다.
        for (auto dot : dragon_curve)
            is_visited[dot.second][dot.first] = true;
    }

    int answer = 0;
    for (int x_idx = 0; x_idx < MAX; x_idx++)
        for (int y_idx = 0; y_idx < MAX; y_idx++)
            // 4 사이드 전부 도달한 곳이면 ++
            if (is_visited[x_idx][y_idx] && is_visited[x_idx][y_idx + 1]
            &&  is_visited[x_idx + 1][y_idx] && is_visited[x_idx + 1][y_idx + 1])
                answer++;

    cout << answer;
    return 0;
}

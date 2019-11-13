#include <bits/stdc++.h>
using namespace std;

constexpr int MAX = 101;

int n, t;
int input[MAX]; // input으로 들어오는 둘째 줄의 값을 저장한다.
bool visited[MAX]; // 방문했는지 체크
vector<int> ans;

// 시작 정점을 매개변수롤 받는다.
void dfs(int x) {
    if (visited[input[x]] == 0) {
        visited[input[x]] = 1;
        dfs(input[x]); // 시작 정점이 가지는 간선으로 깊이 우선 탐색(dfs)를 돌린다.
        visited[input[x]] = 0;
    }

    // 재귀 종료 조건: 시작 정점으로 출발해 시작 정점으로 되돌아왔다면
    if (input[x] == t) {
        ans.push_back(t);
        return;
    }
}

int main() {
    cin >> n;
    // 이 문제의 포인트는 자기 자신으로 출발해서 자기 자신으로 되돌아오는 간선이 몇개인지 찾는 것.

    // 정점당 가지는 간선은 1개씩뿐이니 굳이 벡터를 쓰지 않아도 되긴함.
    for (int i = 1; i <= n; ++i)
        cin >> input[i];

    for (int i = 1; i <= n; ++i) {
        visited[i] = 1;
        t = i; // 시작 정점값 유지
        dfs(i);
        visited[i] = 0;
    }

    cout << ans.size() << "\n";
    for (int i : ans)
        cout << i << "\n";

    return 0;
}
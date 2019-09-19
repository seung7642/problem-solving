#include <iostream>
#include <queue>
using namespace std;

int u,v;
int a[51][51];
int c[51][51];
int n, m, k, t, cnt;
int dx[] = { 0,0,-1,1 };
int dy[] = { -1,1,0,0 };
queue <pair<int, int>> q;

void BFS(int i, int j, int cnt)
{
    q.push(make_pair(i, j));
    c[i][j] = 1;
    while (!q.empty())
    {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();
        for (int i = 0; i < 4; i++) //밭의 4방향 조사
        {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(1<=nx && nx<=n && 1<=ny && ny<=m)
                if (a[nx][ny] == 1 && c[nx][ny] == 0)
                {
                    q.push(make_pair(nx, ny));
                    c[nx][ny] = 1;
                }

        }
    }
}
int main() {
    cin >> t;
    while (t--)
    {
        cin >> m >> n >> k;
        cnt = 0;
        for (int i = 1; i <= n; i++) //초기화
            for (int j = 1; j <= m; j++)
            {
                a[i][j] = 0;
                c[i][j] = 0;
            }
        for (int i = 1; i <= k; i++) //배추의 위치
        {
            cin >> u >> v;
            a[v+1][u+1]=1;
        }
        for (int i = 1; i <= n; i++) //BFS탐색
            for (int j = 1; j <= m; j++)
                if (a[i][j] == 1 && c[i][j] == 0) {
                    BFS(i, j, ++cnt);
                }
        cout << cnt << '\n';
    }
}
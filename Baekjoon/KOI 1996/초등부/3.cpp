#include <bits/stdc++.h>
using namespace std;

class Point {
private:
    int x1, y1, x2, y2;
    static int board[101][101];

public:
    Point() { }
    Point(int x1, int y1, int x2, int y2) : x1(x1), y1(y1), x2(x2), y2(y2) { }

    void setBoard() {
        for (int i = y1; i < y2; ++i) {
            for (int j = x1; j < x2; ++j) {
                board[i][j] = 1;
            }
        }
    }

    int solution() {
        int ans = 0;
        for (int i = 0; i < 101; ++i) {
            for (int j = 0; j < 101; ++j) {
                if (board[i][j]) ans += 1;
            }
        }
        return ans;
    }
};

int Point::board[101][101] = { {0} };


int main() {
    for (int i = 0; i < 4; ++i) {
        int x1, y1, x2, y2;
        cin >> x1 >> y1 >> x2 >> y2;
        Point point(x1, y1, x2, y2);
        point.setBoard();
    }

    Point point;
    int ans = point.solution();
    cout << ans << "\n";

    return 0;
}
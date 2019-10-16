#include <iostream>
#include <queue>
using namespace std;

int N;
queue<int> q;

int main() {
    cin >> N;

    for (int i = 1; i <= N; i++)
        q.push(i);

    while (!(q.size() == 1)) {
        cout << q.front() << " ";
        q.pop();

        if (q.size() == 1) break;
        q.push(q.front());
        q.pop();
    }

    cout << q.front() << "\n";

    return 0;
}

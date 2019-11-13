#include <bits/stdc++.h>
using namespace std;

class DisJointSet {
    int size;
    vector<int> parent;

public:
    DisJointSet(int size) : size(size) {
        parent.resize(size);

        for (int i = 0; i < size; i++) parent[i] = i;
    };

    int find(int v) {
        if (parent[v] == v) return v;

        return parent[v] = find(parent[v]);
    }

    bool merge(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py) return false;
        parent[px] = py; return true;
    }
};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    int n, m;
    cin >> n >> m;

    DisJointSet dis(n+1);
    while (m--) {
        int order, x, y;
        cin >> order >> x >> y;
        if (order) {
            if (dis.find(x) == dis.find(y)) cout << "YES\n";
            else cout << "NO\n"; }
        else {
            dis.merge(x, y);
        }
    }

    return 0;
}
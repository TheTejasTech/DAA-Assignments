#include <iostream>
#include <vector>

using namespace std;

bool isSafe(int vertex, int pos, const vector<vector<int>>& graph, const vector<int>& path) {
    if (graph[path[pos - 1]][vertex] == 0) {
        return false;
    }

    for (int i = 0; i < pos; i++) {
        if (path[i] == vertex) {
            return false;
        }
    }

    return true;
}

void hamiltonianPath(const vector<vector<int>>& graph, int n) {
    vector<int> path(n, -1);
    path[0] = 0;

    int pos = 1;

    while (pos > 0) {
        bool found = false;

        for (int v = path[pos] + 1; v < n; v++) {
            if (isSafe(v, pos, graph, path)) {
                path[pos] = v;
                found = true;
                break;
            }
        }

        if (found) {
            if (pos == n - 1) {
                cout << "\nHamiltonian Path exists:\n";
                for (int i = 0; i < n; i++) {
                    cout << path[i] << " ";
                }
                cout << "\n";
                cout << "Time Complexity (worst case): O(N!)\n";
                cout << "Space Complexity: O(N)\n";
                return;
            }

            pos++;
            path[pos] = -1;
        } else {
            path[pos] = -1;
            pos--;
        }
    }

    cout << "\nNo Hamiltonian Path exists\n";
    cout << "Time Complexity (worst case): O(N!)\n";
    cout << "Space Complexity: O(N)\n";
}

int main() {
    int n;
    cout << "Enter number of vertices: ";
    cin >> n;

    vector<vector<int>> graph(n, vector<int>(n));
    cout << "Enter adjacency matrix:\n";
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> graph[i][j];
        }
    }

    hamiltonianPath(graph, n);
    return 0;
}
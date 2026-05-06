#include <cmath>
#include <iostream>
#include <vector>

using namespace std;

int n;
vector<int> board;
int solutionCount = 0;

bool isSafe(int row, int col) {
    for (int i = 1; i < row; i++) {
        if (board[i] == col || abs(board[i] - col) == abs(i - row)) {
            return false;
        }
    }
    return true;
}

void printBoard() {
    cout << "\nSolution " << solutionCount << ":\n";
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            if (board[i] == j) {
                cout << "Q ";
            } else {
                cout << ". ";
            }
        }
        cout << "\n";
    }
}

void solve(int row) {
    if (row > n) {
        solutionCount++;
        printBoard();
        return;
    }

    for (int col = 1; col <= n; col++) {
        if (isSafe(row, col)) {
            board[row] = col;
            solve(row + 1);
        }
    }
}

int main() {
    cout << "Enter number of queens: ";
    cin >> n;

    board.assign(n + 1, 0);
    solve(1);

    if (solutionCount == 0) {
        cout << "No solution exists for n = " << n << "\n";
    } else {
        cout << "\nTotal solutions = " << solutionCount << "\n";
    }

    cout << "Time Complexity (worst case): O(N!)\n";
    cout << "Space Complexity: O(N)\n";

    return 0;
}
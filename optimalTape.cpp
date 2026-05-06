#include <iostream>
#include <vector>
#include <algorithm>
#include <iomanip>

using namespace std;

int main() {
    int n;
    cout << "Enter number of files: ";
    cin >> n;

    if (n <= 0) {
        cout << "Number of files must be greater than 0.\n";
        return 0;
    }

    vector<int> files(n);
    cout << "Enter file sizes: ";
    for (int i = 0; i < n; i++) {
        cin >> files[i];
    }

    sort(files.begin(), files.end());

    int cumulativeTime = 0;
    int totalRetrievalTime = 0;

    cout << "\nOptimal order of storing files (ascending sizes):\n";
    for (int size : files) {
        cout << size << " ";
    }
    cout << "\n";

    cout << "\nCumulative retrieval times:\n";
    for (int i = 0; i < n; i++) {
        cumulativeTime += files[i];
        totalRetrievalTime += cumulativeTime;
        cout << "File " << (i + 1) << ": " << cumulativeTime << "\n";
    }

    double meanRetrievalTime = static_cast<double>(totalRetrievalTime) / n;

    cout << "\nTotal Retrieval Time: " << totalRetrievalTime << "\n";
    cout << fixed << setprecision(2);
    cout << "Mean Retrieval Time: " << meanRetrievalTime << "\n";

    return 0;
}

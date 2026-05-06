#include <iostream>
using namespace std;

void findMinMax(int arr[], int low, int high, int &min, int &max) {
    
    if (low == high) {
        min = arr[low];
        max = arr[low];
        return;
    }
    
    if (high == low + 1) {
        if (arr[low] < arr[high]) {
            min = arr[low];
            max = arr[high];
        } else {
            min = arr[high];
            max = arr[low];
        }
        return;
    }
    
    int mid = (low + high) / 2;
    int min1, max1, min2, max2;
    
    findMinMax(arr, low, mid, min1, max1);
    
    findMinMax(arr, mid + 1, high, min2, max2);
    
    min = (min1 < min2) ? min1 : min2;
    max = (max1 > max2) ? max1 : max2;
}

int main() {
    int n;
    
    cout << "Enter the number of elements: ";
    cin >> n;
    
    int arr[n];
    cout << "Enter " << n << " elements: ";
    for (int i = 0; i < n; i++) {
        cin >> arr[i];
    }
    
    int min, max;
    findMinMax(arr, 0, n - 1, min, max);
    
    cout << "\nMinimum element: " << min << endl;
    cout << "\nMaximum element: " << max << endl;
    
    return 0;
}

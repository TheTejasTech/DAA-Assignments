#include<bits/stdc++.h>
using namespace std; 

//helper functions - add & sub

vector<vector<int>> add(const vector<vector<int>>& A, const vector<vector<int>>& B){
    int n = A.size();
    vector<vector<int>> result (n , vector<int>(n));
    for (int i = 0; i < n; i++){
        for (int j = 0; j < n; j++){
            result[i][j] = A[i][j] + B[i][j];       
        }   
    }

    return result;
}

vector<vector<int>> sub(const vector<vector<int>>& A, const vector<vector<int>>& B){
    int n = A.size();
    vector<vector<int>> result (n , vector<int>(n));
    for (int i = 0; i < n; i++){
        for (int j = 0; j < n; j++){
            result[i][j] = A[i][j] - B[i][j];       
        }   
    }

    return result;
}

vector<vector<int>> strassen(const vector<vector<int>>& A, const vector<vector<int>>& B){
    int n = A[0].size();
    if (n == 1){
        return {{A[0][0] * B[0][0]}};
    }

    int k = n / 2;

    vector<vector<int>> A11(k, vector<int>(k, 0)), A12(k, vector<int>(k, 0)), A21(k, vector<int>(k, 0)), A22(k, vector<int>(k, 0));
    vector<vector<int>> B11(k, vector<int>(k, 0)), B12(k, vector<int>(k, 0)), B21(k, vector<int>(k, 0)), B22(k, vector<int>(k, 0));

    for (int i = 0; i < k; i++){
        for (int j = 0; j < k; j++){
            A11[i][j] = A[i][j];
            A12[i][j] = A[i][j+k];
            A21[i][j] = A[i+k][j];
            A22[i][j] = A[i+k][j+k];

            B11[i][j] = B[i][j];
            B12[i][j] = B[i][j+k];
            B21[i][j] = B[i+k][j];
            B22[i][j] = B[i+k][j+k];
        }
    }

    //making strassens 7 matrices

    auto M1 = strassen(add(A11, A22), add(B11, B22));
    auto M2 = strassen(add(A21, A22), B11);
    auto M3 = strassen(A11, sub(B12, B22));
    auto M4 = strassen(A22, sub(B21, B11));
    auto M5 = strassen(add(A11, A12), B22);
    auto M6 = strassen(sub(A21, A11), add(B11, B12));
    auto M7 = strassen(sub(A12, A22), add(B21, B22));

    // combining the matrices formed

    auto C11 = add(sub(add(M1, M4), M5), M7);
    auto C12 = add(M3, M5);
    auto C21 = add(M2, M4);
    auto C22 = add(sub(add(M1, M3), M2), M6);

    vector<vector<int>> C(n, vector<int>(n));

    for (int i = 0; i < k; i++){
        for (int j = 0; j < k; j++){
            C[i][j] = C11[i][j];
            C[i][j+k] = C12[i][j];
            C[i+k][j] = C21[i][j];
            C[i+k][j+k] = C22[i][j];        
        }
    }

    return C;
}

int main(){
    vector<vector<int>> A = {
    {12,  5,  2,  8},
    {-3,  14,  7,  1},
    {0, 9, 22, 4},
    {6,2, 11, 15}
};

vector<vector<int>> B = {
    {7,1,14,13},
    {5,12,0, 8},
    {-2, 6, 9,  13},
    {1, 4, 1, 5}
};

    auto result = strassen(A, B);
    
    cout << "Result of matrix multiplication:\n";
    for (int i = 0; i < result.size(); i++){
        for (int j = 0; j < result[i].size(); j++){
            cout << result[i][j] << " ";
        }
        cout << "\n";
    }

    return 0;
}
#include <iostream>
#include <vector>

using namespace std;

vector<vector<int>> matmul(vector<vector<int> > &A, vector<vector<int>> &B);

int main()
{
    vector<vector<int>> m1 = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}};
    vector<vector<int>> m2 = {
        {5, 0, 2},
        {2, 3, 3},
        {7, 10, 15}};

    vector<vector<int> > product = matmul(m1, m2);
    for (auto &row : product)
    {
        for (auto &col : row)
        {
            cout << col << " ";
        }
        cout << endl;
    }
    return 0;
}

vector<vector<int> > matmul(vector<vector<int>> &A, vector<vector<int> > &B)
{
    if (A.size() == 0 && B.size() == 0)
        return vector<vector<int>>();
    if (A.size() == 0 || A[0].size() != B.size())
        throw invalid_argument("Matrix dimensions do not match");
    int vector_length = B.size();
    if (vector_length == 0)
        return vector<vector<int>>();

    int result_rows = A.size();
    int result_cols = B[0].size();

    vector<vector<int>> result(result_rows, vector<int>(result_cols, 0));
    for (int i = 0; i < result_rows; i++)
    {
        for (int j = 0; j < result_cols; j++)
        {
            for (int k = 0; k < vector_length; k++)
            {
                result[i][j] += A[i][k] * B[k][j];
            }
        }
    }
    return result;
}

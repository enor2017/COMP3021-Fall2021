# Lab 2: From C++ to Java


This lab covers the following topics:

- Rewriting a method in C++ using Java
- Using Gradle to build and test projects

## Prerequisites

In this lab, we assume that you have successfully installed JDK 16 and IntelliJ IDEA as taught in Lab 1.
Also, you should be able to load a gradle project and run its test cases within IntelliJ IDEA, which is also part of Lab 1.

> **IMPORTANT NOTE:** Please read this document carefully before asking questions.

## Task of Lab 2

In this lab, you will need to read a C++ program that perform matrix multiplication and rewrite the specified method using Java.
After rewriting the program, you should run the test cases provided by us, and you may need to fix the problem in your program until all the test cases pass.

## Matrix Multiplication Program
The following code snippet shows the C++ version of the method you need to rewrite.
Specifically, its behaviors are as follows.
- `A` is the left matrix and `B` is the right matrix.
- If both matrices are empty, return an empty matrix.
- If the dimensions of the two matrices do not match, throw an `invalid_argument` with `"Mateix dimensions do not match"` (In the Java version, you should throw an `java.lang.IllegalArgumentException`) .
- Then it generates the elements of the result matrix row by row.
- Finally, it returns the result

What you need to do is to implement the method `matMul` (marked with `TODO`) in `Lab2.java` referencing the C++ version.

> **Hint:** In Java, you can not modify an element in a `List` inplace using `+=`, you need to retrieve its value, modify it, and put it back to the `List`.

```c++
vector<vector<int>> matmul(vector<vector<int>> &A, vector<vector<int>> &B)
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
```
## Lab 2 Submission

You need to submit exactly one file (i.e., `Lab2.java`) to CASS.
An empty submission or submitting extra files will lead to deduction of your lab score.

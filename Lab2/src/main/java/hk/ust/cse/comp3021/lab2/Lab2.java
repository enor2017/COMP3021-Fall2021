package hk.ust.cse.comp3021.lab2;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public final class Lab2 {

    public static List<List<Integer>> matMul(
            @NotNull final List<List<Integer>> left,
            @NotNull final List<List<Integer>> right) {
        if (left.size() == 0 && right.size() == 0) {
            return new ArrayList<>();
        }
        if (left.size() == 0 || left.get(0).size() != right.size()) {
            throw new IllegalArgumentException("Matrix dimensions do not match");
        }

        int vector_length = right.size();
        if (vector_length == 0) {
            return new ArrayList<>();
        }
        int result_rows = left.size();
        int result_cols = right.get(0).size();
        ArrayList<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < result_rows; ++i) {
            ArrayList<Integer> temp_row = new ArrayList<>();
            for (int j = 0; j < result_cols; ++j) {
                int sum = 0;
                for (int k = 0; k < vector_length; ++k) {
                    sum += left.get(i).get(k) * right.get(k).get(j);
                }
                temp_row.add(sum);
            }
            result.add(temp_row);
        }
        return result;
    }

}

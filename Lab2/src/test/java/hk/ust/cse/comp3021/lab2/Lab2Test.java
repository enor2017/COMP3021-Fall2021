package hk.ust.cse.comp3021.lab2;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public final class Lab2Test {

    @Test
    public void squareMatrix() {
        var m1 = createMatrix(
                new int[]{1, 2, 3},
                new int[]{1, 2, 3},
                new int[]{1, 2, 3}
        );
        var m2 = createMatrix(
                new int[]{1, 2, 3},
                new int[]{1, 2, 3},
                new int[]{1, 2, 3}
        );
        var result = Lab2.matMul(m1, m2);
        var expected = createMatrix(
                new int[]{6, 12, 18},
                new int[]{6, 12, 18},
                new int[]{6, 12, 18}
        );
        assertTrue(matrixEquals(expected, result));
    }

    @Test
    public void nonMatchingMatrix() {
        var m1 = createMatrix(
                new int[]{1, 2, 3},
                new int[]{1, 2, 3},
                new int[]{1, 2, 3}
        );
        var m2 = createMatrix(
                new int[]{1, 2, 3},
                new int[]{1, 2, 3}
        );
        var e = assertThrows(IllegalArgumentException.class, () -> Lab2.matMul(m1, m2));
        assertEquals(e.getMessage(), "Matrix dimensions do not match");
    }

    @Test
    public void emptyLeft() {
        var m1 = createMatrix();
        var m2 = createMatrix(
                new int[]{1, 2, 3},
                new int[]{1, 2, 3}
        );
        var e = assertThrows(IllegalArgumentException.class, () -> Lab2.matMul(m1, m2));
        assertEquals(e.getMessage(), "Matrix dimensions do not match");
    }

    @Test
    public void emptyRight() {
        var m1 = createMatrix(
                new int[]{1, 2, 3},
                new int[]{1, 2, 3}
        );
        var m2 = createMatrix();
        var e = assertThrows(IllegalArgumentException.class, () -> Lab2.matMul(m1, m2));
        assertEquals(e.getMessage(), "Matrix dimensions do not match");
    }

    @Test
    public void bothEmpty() {
        var m1 = createMatrix();
        var m2 = createMatrix();
        var result = Lab2.matMul(m1, m2);
        assertEquals(0, result.size());
    }

    @Test
    public void nonSquareMatrix() {
        var m1 = createMatrix(
                new int[]{1, 0},
                new int[]{0, 1}

        );
        var m2 = createMatrix(
                new int[]{2, 3, 3},
                new int[]{2, 3, 3}
        );
        var result = Lab2.matMul(m1, m2);
        var expected = createMatrix(
                new int[]{2, 3, 3},
                new int[]{2, 3, 3}
        );
        assertTrue(matrixEquals(expected, result));
    }

    private static @NotNull List<List<Integer>> createMatrix(int[] @NotNull ... rows) {
        var result = new ArrayList<List<Integer>>();
        for (var row : rows) {
            var rowList = new ArrayList<Integer>();
            for (var element : row) {
                rowList.add(element);
            }
            result.add(rowList);
        }
        return result;
    }

    private static boolean matrixEquals(
            @NotNull List<List<Integer>> left,
            @NotNull List<List<Integer>> right
    ) {
        if (left.size() != right.size())
            return false;
        int leftRows = left.size();
        int leftCols = 0;
        if (left.size() > 0) {
            leftCols = left.get(0).size();
        }
        if (right.get(0).size() != leftCols)
            return false;
        for (int row = 0; row < leftRows; row++) {
            for (int col = 0; col < leftCols; col++) {
                try {
                    var leftValue = left.get(row).get(col);
                    var rightValue = right.get(row).get(col);
                    if (!leftValue.equals(rightValue))
                        return false;
                } catch (IndexOutOfBoundsException e) {
                    return false;
                }
            }
        }
        return true;
    }
}

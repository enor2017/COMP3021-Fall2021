package hk.ust.cse.comp3021.lab6.operator;

import hk.ust.cse.comp3021.lab6.structure.Expression;
import hk.ust.cse.comp3021.lab6.structure.Operation;
import hk.ust.cse.comp3021.lab6.structure.Operator;
import hk.ust.cse.comp3021.lab6.structure.Value;
import hk.ust.cse.comp3021.lab6.value.IntNumber;

import java.math.BigInteger;
import java.util.List;

/**
 * Subtraction should implement {@link Operator}, and will be used to construct {@link Operation} objects.
 * All operands are instances of {@link IntNumber}.
 * Hint: BigInteger.subtract(BigInteger) are useful for implementing eval()
 * Note: If the number of operands is 1, return the negation. Otherwise, subtract the rest of the operands from
 * the first operand
 */

public class Subtraction implements Operator {
    public Subtraction() {}

    @Override
    public Value operate(List<Expression> operands) {
        if (operands.size() == 1) {
            return new IntNumber(new BigInteger("-" + operands.get(0).eval().toString()));
        }
        BigInteger res = new BigInteger(operands.get(0).eval().toString());
        for (int i = 1; i < operands.size(); ++i) {
            res = res.subtract(new BigInteger(operands.get(i).eval().toString()));
        }
        return new IntNumber(res);
    }

    @Override
    public String symbol() {
        return "-";
    }
}

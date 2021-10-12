package hk.ust.cse.comp3021.lab6.operator;

import hk.ust.cse.comp3021.lab6.structure.Expression;
import hk.ust.cse.comp3021.lab6.structure.Operation;
import hk.ust.cse.comp3021.lab6.structure.Operator;
import hk.ust.cse.comp3021.lab6.structure.Value;
import hk.ust.cse.comp3021.lab6.value.IntNumber;

import java.math.BigInteger;
import java.util.List;


/**
 * Multiplication should implement {@link Operator}, and will be used to construct {@link Operation} objects.
 * All operands are instances of {@link IntNumber}.
 * Hint: Use the constant BigInteger.ONE and the method BigInteger.multiply(BigInteger) to implement the eval method
 */
public class Multiplication implements Operator {
    public Multiplication() {}

    @Override
    public Value operate(List<Expression> operands) {
        BigInteger res = BigInteger.ONE;
        for (var x : operands) {
            res = res.multiply(new BigInteger(x.eval().toString()));
        }
        return new IntNumber(res);
    }

    @Override
    public String symbol() {
        return "*";
    }
}

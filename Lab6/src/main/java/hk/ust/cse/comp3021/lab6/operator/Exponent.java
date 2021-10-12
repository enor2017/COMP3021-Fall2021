package hk.ust.cse.comp3021.lab6.operator;

import hk.ust.cse.comp3021.lab6.structure.Expression;
import hk.ust.cse.comp3021.lab6.structure.Operation;
import hk.ust.cse.comp3021.lab6.structure.Operator;
import hk.ust.cse.comp3021.lab6.structure.Value;
import hk.ust.cse.comp3021.lab6.value.IntNumber;

import java.math.BigInteger;
import java.util.List;


/**
 * Exponent should implement {@link Operator}, and will be used to construct {@link Operation} objects.
 * All operands are instances of {@link IntNumber}.
 * Hint: BigInteger.pow(int)
 */

public class Exponent implements Operator {
    public Exponent() {}

    @Override
    public Value operate(List<Expression> operands) {
        if (operands.size() != 2) {
            throw new IllegalArgumentException("Why Exponent has not 2 operands?");
        }
        BigInteger res = (new BigInteger(operands.get(0).toString())).pow(Integer.parseInt(operands.get(1).toString()));
        return new IntNumber(res);
    }

    @Override
    public String symbol() {
        return "^";
    }
}
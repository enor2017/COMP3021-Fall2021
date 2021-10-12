package hk.ust.cse.comp3021.lab6.operator;

import hk.ust.cse.comp3021.lab6.structure.Expression;
import hk.ust.cse.comp3021.lab6.structure.Operation;
import hk.ust.cse.comp3021.lab6.structure.Operator;
import hk.ust.cse.comp3021.lab6.structure.Value;
import hk.ust.cse.comp3021.lab6.value.IntNumber;


import java.math.BigInteger;
import java.util.List;


/**
 * Division should implement {@link Operator}, and will be used to construct {@link Operation} objects.
 * All operands are instances of {@link IntNumber}.
 * Note: if there is only one operand, return 1 / the operand (integer division).
 * Otherwise, divide the first operand by the rest of operands.
 */

public class Division implements Operator {
    public Division() {}

    @Override
    public Value operate(List<Expression> operands) {
        if (operands.size() == 1) {
            return new IntNumber(BigInteger.ONE.divide(new BigInteger(operands.get(0).eval().toString())));
        }
        BigInteger res = new BigInteger(operands.get(0).eval().toString());
        for (int i = 1; i < operands.size(); ++i) {
            res = res.divide(new BigInteger(operands.get(i).eval().toString()));
        }
        return new IntNumber(res);
    }

    @Override
    public String symbol() {
        return "/";
    }
}
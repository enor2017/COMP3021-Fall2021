package hk.ust.cse.comp3021.lab6.operator;


import hk.ust.cse.comp3021.lab6.structure.Expression;
import hk.ust.cse.comp3021.lab6.structure.Operation;
import hk.ust.cse.comp3021.lab6.structure.Operator;
import hk.ust.cse.comp3021.lab6.structure.Value;
import hk.ust.cse.comp3021.lab6.value.IntNumber;

import java.math.BigInteger;
import java.util.List;

/**
 * Addition should implement {@link Operator}, and will be used to construct {@link Operation} objects.
 * All operands are instances of {@link IntNumber}.
 */
public class Addition implements Operator {

    public Addition() {}

    @Override
    public Value operate(List<Expression> operands) {
        BigInteger res = BigInteger.valueOf(0);
        for (var x : operands) {
            res = res.add(new BigInteger(x.eval().toString()));
        }
        return new IntNumber(res);
    }

    @Override
    public String symbol() {
        return "+";
    }
}

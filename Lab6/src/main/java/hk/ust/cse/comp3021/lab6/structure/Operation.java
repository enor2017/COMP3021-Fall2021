package hk.ust.cse.comp3021.lab6.structure;

import hk.ust.cse.comp3021.lab6.value.IntNumber;

import java.util.ArrayList;
import java.util.StringJoiner;


/**
 * Operation should implement {@link Expression}.
 * Operation consists of {@link Operator} and a list of {@link Expression} as its operands.
 */

public class Operation implements Expression {
    private final Operator operator;
    private final ArrayList<Expression> operands;

    public Operation(Operator operator, ArrayList<Expression> operands) {
        this.operands = operands;
        this.operator = operator;
    }

    @Override
    public Value eval() {
        return operator.operate(operands);
    }

    @Override
    public String toString() {
        if (operands.size() == 1) {
            // print a value
            return operands.get(0).toString();
        } else {
            // print an expression
            return "(" + operator.symbol() + " " + operands.get(0).toString() + " "
                    + operands.get(1).toString() + ")";
        }
    }
}
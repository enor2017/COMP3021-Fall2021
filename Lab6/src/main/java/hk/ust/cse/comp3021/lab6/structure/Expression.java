package hk.ust.cse.comp3021.lab6.structure;

import java.math.BigInteger;

/**
 * Expression has one public abstract method, eval(), which returns a {@link Value} which
 * represents the calculation result of this expression.
 * Another public abstract method is toString(), which returns a string to print the expression.
 */

public interface Expression {
    Value eval();

    @Override
    String toString();
}

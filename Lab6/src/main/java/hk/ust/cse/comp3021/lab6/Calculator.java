package hk.ust.cse.comp3021.lab6;

import hk.ust.cse.comp3021.lab6.structure.Expression;

import java.util.*;

public class Calculator {
    public static void main(String[] args) {
        String line;
        try (Scanner reader = new Scanner(System.in)) {
            Parser parser = new Parser();
            System.out.println("Please enter an expression:");
            while (true) {
                System.out.print("> ");
                line = reader.nextLine();
                if (line.isEmpty()) {
                    System.out.println("Goodbye!");
                    break;
                }
                Expression expr;
                try {
                    expr = parser.parse(line);
                } catch (Exception ex) {
                    System.err.println("Invalid expression: " + ex.getMessage());
                    continue;
                }
                System.out.println("expr = " + expr);
                System.out.printf("res = %s%n%n", expr.eval());
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            System.exit(-1);
        }
    }

}

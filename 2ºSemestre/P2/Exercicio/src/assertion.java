/* import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Stack;

public final class assertion {

    /**
     * Removes all characters that are not operators, whitespace, or digits
     *
     * @param expr the expression to parse
     * @return the parsed expression

    private static String parseExpression(String expr) {
        String parsedExpr = expr.replaceAll("[^\\^\\*\\+\\-\\d/\\s]", "");
        String trimmedExpr = parsedExpr.replaceAll("\\s+", " ");
        return trimmedExpr;
    }

    /**
     * Computes the outcome of a given expression in Reverse Polish Notation
     *
     * @param expr the expression to compute

    public static void compute(String expr) throws
            ArithmeticException,
            EmptyStackException {
        String validExpr = parseExpression(expr);
        Stack<Double> stack = new Stack<>();

        System.out.println(validExpr);
        System.out.println("Input\tOperation\tStack after");

        for (String token : validExpr.split("\\s")) {
            System.out.print(token + "\t");

            double secondOperand = 0.0;
            double firstOperand = 0.0;

            switch (token) {
                case "+":
                    System.out.print("Operate\t\t");

                    secondOperand = stack.pop();
                    firstOperand = stack.pop();

                    stack.push(firstOperand + secondOperand);
                    break;
                case "-":
                    System.out.print("Operate\t\t");

                    secondOperand = stack.pop();
                    firstOperand = stack.pop();

                    stack.push(firstOperand - secondOperand);
                    break;
                case "*":
                    System.out.print("Operate\t\t");

                    secondOperand = stack.pop();
                    firstOperand = stack.pop();

                    stack.push(firstOperand * secondOperand);
                    break;
                case "/":
                    System.out.print("Operate\t\t");

                    secondOperand = stack.pop();
                    firstOperand = stack.pop();

                    if (secondOperand == 0.0) {
                        throw new ArithmeticException("Cannot divide by zero!");
                    }

                    stack.push(firstOperand / secondOperand);
                    break;
                case "^":
                    System.out.print("Operate\t\t");

                    secondOperand = stack.pop();
                    firstOperand = stack.pop();

                    stack.push(Math.pow(firstOperand, secondOperand));
                    break;
                default:
                    System.out.print("Push\t\t");
                    stack.push(Double.parseDouble(token));
                    break;
            }

            System.out.println(stack);
        }

        System.out.println("Final Answer: " + stack.pop());
    }


    public static void main(String[] args) {
        try {
            String expr = Arrays.toString(args);
            System.out.println(expr);
            compute(expr);
        } catch (Throwable err) {
            System.out.println(err.getMessage());
        }
    }
}
*/
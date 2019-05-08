package trabalho.trabalho1;

import java.util.StringTokenizer;

public class InfixToPost extends Main{

    String exp = "";
    ArrayStack<String> operatores;
    String numbers = "";
    boolean verbose;


    public InfixToPost(){
        exp = "(3-1+3*6)/2";
        operatores= new ArrayStack<>(exp.length());
        verbose = true;
        sendTo(exp);

    }

    public InfixToPost(String expr){
        exp = expr;
        operatores= new ArrayStack<>(exp.length());
        verbose = true;
        sendTo(exp);
    }

    public InfixToPost(String expr, boolean verb){
        exp = expr;
        operatores= new ArrayStack<>(exp.length());
        verbose = verb;
        sendTo(exp);
    }


    public int characters(String c)
    {
        switch(c){
            case "+":
            case "-":
                return 1;

            case "*":
            case "/":
                return 2;

            case "(":
            case ")":
                return 3;
        }

        return 0;
    }



    public void sendTo(String exp){
        String output = "";
        StringTokenizer expre = new StringTokenizer(exp, "+-/*() ", true);

        while (expre.hasMoreTokens()) {
            String token = expre.nextToken();

            if (verbose) {
                if (characters(token) == 0) {
                    output += token + " ";
                    System.out.println("output = "+ output);

                } else {
                    if (token.equals(")")) {
                        while (!operatores.empty() && !operatores.top().equals("(")) {
                            output += operatores.pop() + " ";
                            System.out.println("output = operadores.pop()");

                        }
                        operatores.pop();
                        System.out.println("operadores.pop()");
                    } else {
                        if (operatores.empty() || token.equals("(")) {
                            operatores.push(token);
                            System.out.println("operadores.push("+token+")");
                        } else if (characters(token) > characters((String) operatores.top())) {
                            operatores.push(token);
                            System.out.println("operadores.push("+token+")");
                        } else {
                            while (!operatores.top().equals("(") && !operatores.empty() && characters(operatores.top()) >= characters(token)) {
                                output += operatores.pop() + " ";
                                System.out.println("output = operadores.pop()");

                                if (operatores.empty()) {
                                    break;
                                }
                            }
                            operatores.push(token);
                            System.out.println("operadores.push("+token+")");
                        }
                    }
                }
            }
            else{
                if (characters(token) == 0) {
                    output += token + " ";


                } else {
                    if (token.equals(")")) {
                        while (!operatores.empty() && !operatores.top().equals("(")) {
                            output += operatores.pop() + " ";
                        }
                        operatores.pop();
                    } else {
                        if (operatores.empty() || token.equals("(")) {
                            operatores.push(token);
                        } else if (characters(token) > characters((String) operatores.top())) {
                            operatores.push(token);
                        } else {
                            while (!operatores.top().equals("(") && !operatores.empty() && characters(operatores.top()) >= characters(token)) {
                                output += operatores.pop() + " ";

                                if (operatores.empty()) {
                                    break;
                                }
                            }
                            operatores.push(token);
                        }
                    }
                }
            }
        }


        while(!operatores.empty() && !operatores.top().equals("("))
        {
            if (verbose){
                operatores.toString();
                output += operatores.pop() + " ";
                System.out.println("output = operadores.pop()");
                System.out.println(output);
            }
            else{
                operatores.toString();
                output += operatores.pop() + " ";
                System.out.println(output);
            }


    }

    }
}

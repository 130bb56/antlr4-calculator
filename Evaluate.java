/*

Calculate The Input String
And Return the result

*/


import java.util.Stack;

class Evaluate {
    public static Stack<Double> finalValue = new Stack<>();
    public void printStack() {
        for (Double value : finalValue) {
            System.out.println(value);
        }
    }

    public AstNodes evaluate(double leftValue, double rightValue, String op, int depth) {
        double retValue;
        switch (op) {
            case "MUL": {
                retValue = leftValue * rightValue;
                break;
            } case "DIV": {
                retValue = leftValue / rightValue;
                break;
            } case "ADD": {
                retValue = leftValue + rightValue;
                break;
            } default: retValue = leftValue - rightValue;

        }
        return new AstNodes(op, "NUM", retValue, depth);
    }
}
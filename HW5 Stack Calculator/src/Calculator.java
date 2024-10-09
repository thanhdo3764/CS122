import java.util.Scanner;

public class Calculator {
    double[] stack;
    int size;
    double x;
    double y;
    double z;
    /**
     * _Part 1: Implement this constructor_
     *
     * Create a new Calculator with d slots in the stack
     * @param d - number of spaces in the stack
     */
    public Calculator(int d) {
        stack = new double[d];
        size = 0;
        x = 0;
        y = 0;
        z = 0;
    }

    /**
     * _Part 2: Implement this method_
     *
     * Push the specified double onto the stack
     * @param d - the value to push
     * throw an IllegalStateException if the stack has 10 or more values.
     */
    private void push(double d) {
        if (size >= 10) {
            throw new IllegalStateException("Stack has 10 or more values");
        }
        stack[size] = d;
        size++;
    }

    /**
     * _Part 3: Implement this method_
     *
     * Pop the top value off the stack
     * throw an IllegalStateException if the stack is currently empty.
     */
    private double pop() {
        double local;
        if (size == 0) throw new IllegalStateException("Stack is empty");
        else {
            local = stack[size-1];
            stack[size-1] = 0;
        }
        size--;
        return local;
    }

    /**
     * _Part 4: Implement this method_
     *
     * Calculate the value from a String of operations.
     *
     * Required operations:
     *  "+" - adds the top two entries on the stack
     *  "*" - multiplies the top two entries on the stack
     *  "-" - subtracts the top entry in the stack from the 2nd entry in the stack
     *  "/" - divides the 2nd entry in the stack by the top entry in the stack
     *  "^" - raises the 2nd entry in the stack to the power indicated by the top entry in the stack
     *  "lg" - takes the lg (log base 2) of the top entry in the stack
     *
     *  Operations for more practice: Variables
     *   expand the use of the calculator by supporting the use of
     *   three variables 'x', 'y', and 'z' in expressions. Specifically
     *   for each variable, there should be a way to set its value 
     *   the tokens 'setx', 'sety', and 'setz' respectively, and a way to 
     *   read its value -- the tokens: 'x', 'y', and 'z' respectively.
     *   With these new operators we should be able to evaluate
     *   expressions such as:
     *   "10 4 + setx" (set the 'x' variable to 14)
     *   "42 x /"      (divide 42 by the value stored for 'x' -- currently 14)
     *   "x x -"       (subtract 14 from 14)
     *
     * @param s - the string representing a mathematic expression
     * throw an IllegalArgumentException if a specified operator is unknown.
     */
    public double calculate(String s) {
        Scanner myScan = new Scanner(s);
        double value1;
        double value2 = 0;
        String operator;
        while (myScan.hasNext()) {
            // pushes all consecutive doubles
            while (myScan.hasNextDouble()) {
                push(myScan.nextDouble());
            }
            // only calculate if there is any tokens remaining
            if (myScan.hasNext()) {
                operator = myScan.next();
                // x,y,z are not operators, so push the value of x,y,z then restart loop
                if (operator.equals("x")) {
                    push(x);
                    continue;
                }
                if (operator.equals("y")) {
                    push(y);
                    continue;
                }
                if (operator.equals("z")) {
                    push(z);
                    continue;
                }
                value1 = pop();
                // log does not have a value2
                if (!operator.equals("lg") && !operator.contains("set")) value2 = pop();
                // addition
                if (operator.equals("+")) {
                    push(value2+value1);
                    continue;
                }
                // subtraction
                if (operator.equals("-")) {
                    push(value2-value1);
                    continue;
                }
                // multiplication
                if (operator.equals("*")) {
                    push(value2*value1);
                    continue;
                }
                // division
                if (operator.equals("/")) {
                    push(value2 / value1);
                    continue;
                }
                // exponential
                if (operator.equals("^")) {
                    push(Math.pow(value2, value1));
                    continue;
                }
                // logarithmic
                if (operator.equals("lg")) {
                    push(Math.log(value1)/Math.log(2));
                    continue;
                }
                // setx
                if (operator.equals("setx")) {
                    x = value1;
                    push(value1);
                    continue;
                }
                // sety
                if (operator.equals("sety")) {
                    y = value1;
                    push(value1);
                    continue;
                }
                // setx
                if (operator.equals("setz")) {
                    z = value1;
                    push(value1);
                }

            }
        }
        return pop();
    }
    public void print() {
        int i;
        System.out.println("Size: " + size);
        System.out.print("[");
        for (i = 0; i < size-1; i++) {
            System.out.print(stack[i]+", ");
        }
        if (size > 1) System.out.print(stack[i]);
        System.out.print("]\n");
    }

    public static void main(String[] args) {
        Calculator c = new Calculator(5);
        System.out.println(c.calculate("10 4 +") + " should equal 14");
        System.out.println(c.calculate("4 2 /") + " should equal 2");
        System.out.println(c.calculate("10 4 + 3 * 2 /") + " should equal 21");
        System.out.println(c.calculate("16 lg") + " should equal 4");
        System.out.println(c.calculate("16 4 -") + " should equal 12");
        System.out.println(c.calculate("5 16 4 + -") + " should equal -15");
        System.out.println(c.calculate("5 20 -") + " should equal -15");
        System.out.println(c.calculate("5") + " should equal 5");
        System.out.println(c.calculate("10 sety") + " should equal 10");
        System.out.println(c.calculate("y 1 + setz") + " should equal 11");
        System.out.println(c.calculate("y z +") + " should equal 21");
    }
}

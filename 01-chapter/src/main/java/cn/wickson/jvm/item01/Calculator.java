package cn.wickson.jvm.item01;

/**
 * Java To Class
 */
public class Calculator {
    private int result;

    public Calculator() {
        this.result = 0;
    }

    public void add(int a, int b) {
        this.result = a + b;
    }

    public void subtract(int a, int b) {
        this.result = a - b;
    }

    public int getResult() {
        return this.result;
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.add(5, 3);
        System.out.println("Result of addition: " + calc.getResult());

        calc.subtract(10, 4);
        System.out.println("Result of subtraction: " + calc.getResult());
    }
}

class ExceptionA1 extends Exception {
    public ExceptionA1(String message) {
        super(message);
    }
}

class ExceptionB1 extends ExceptionA {
    public ExceptionB1(String message) {
        super(message);
    }
}

class ExceptionC1 extends ExceptionB {
    public ExceptionC1(String message) {
        super(message);
    }
}

public class Exc4{
    public static void main(String[] args) {
        try {
            // Simulate an exception of type ExceptionC
            throw new ExceptionC1("This is an ExceptionC.");
        } catch (ExceptionA e) {
            // Catch block for ExceptionA, which will catch exceptions of types ExceptionA, ExceptionB, and ExceptionC
            System.out.println("Caught an exception of type ExceptionA: " + e.getMessage());
        }
    }
}

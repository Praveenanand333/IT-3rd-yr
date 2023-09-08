class ExceptionA extends Exception {
    public ExceptionA(String message) {
        super(message);
    }
}

class ExceptionB extends ExceptionA {
    public ExceptionB(String message) {
        super(message);
    }
}

public class Exc {
    public static void main(String[] args) {
        try {
            throw new ExceptionA("ExceptionA occurred.");
        } catch (Exception exception) {
            System.out.println("Exception caught: " + exception.getMessage());
        }

        try {
            throw new ExceptionB("ExceptionB occurred.");
        } catch (Exception exception) {
            System.out.println("Exception caught: " + exception.getMessage());
        }

        try {
            throw new NullPointerException("NullPointerException occurred.");
        } catch (Exception exception) {
            System.out.println("Exception caught: " + exception.getMessage());
        }

        try {
            throw new IllegalArgumentException("IllegalArgumentException occurred.");
        } catch (Exception exception) {
            System.out.println("Exception caught: " + exception.getMessage());
        }
    }
}

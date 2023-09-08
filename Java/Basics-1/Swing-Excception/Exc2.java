
class MyException extends Exception {
    public MyException(String message) {
        super(message);
    }
}


class Exceptions extends MyException {
    public Exceptions(String message) {
        super(message);
    }
}


class ExceptionC extends MyException {
    public ExceptionC(String message) {
        super(message);
    }
}

public class Exc2 {
    public static void main(String[] args) {
        try {

            throw new ExceptionC("ExceptionC occurred.");
        } catch (MyException exception) {

            System.out.println("Caught exception: " + exception.getMessage());
        }

        try {
            throw new Exceptions("Exceptions occurred.");
        } catch (MyException exception) {

            System.out.println("Caught exception: " + exception.getMessage());
        }
    }
}

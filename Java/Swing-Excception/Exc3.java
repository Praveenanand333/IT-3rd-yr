class SomeClass {
    public SomeClass() throws Exception {
        throw new Exception("Constructor Failure: Something went wrong!");
    }
}

public class Exc3{
    public static void main(String[] args) {
        try {
            SomeClass someObject = new SomeClass();
        } catch (Exception e) {
            System.out.println("Caught an exception: " + e.getMessage());
        }
    }
}

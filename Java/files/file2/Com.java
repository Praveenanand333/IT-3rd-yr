import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Com {
    public static void main(String[] args) throws IOException {
        // Create and populate sample account records
        List<Account> sampleAccounts = new ArrayList<>();
        sampleAccounts.add(new Account("1001", "John Smith", 1000.00));
        sampleAccounts.add(new Account("1002", "Jane Doe", 500.50));
        sampleAccounts.add(new Account("1003", "Alice Johnson", 750.25));

        // Serialize and save the sample account records to oldmast.ser
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("oldmast.ser"));
        for (Account account : sampleAccounts) {
            objectOutputStream.writeObject(account);
        }
        objectOutputStream.close();

        System.out.println("Sample data written to oldmast.ser.");
    }
}

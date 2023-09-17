import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TransactionRecord implements Serializable {
    private String accountNumber;
    private double amount;

    public TransactionRecord(String accountNumber, double amount) {
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }
}

class Account implements Serializable {
    private String accountNumber;
    private String name;
    private double balance;
    private List<TransactionRecord> transactions;

    public Account(String accountNumber, String name, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void addTransaction(TransactionRecord transactionRecord) {
        transactions.add(transactionRecord);
    }

    public List<TransactionRecord> getTransactions() {
        return transactions;
    }
}

class FileMatch {
    private Map<String, Account> masterRecords = new HashMap<>();
    private PrintWriter logFile;

    public FileMatch() throws FileNotFoundException {
        logFile = new PrintWriter("log.txt");
    }

    public void readOldMastFile(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename));
        while (true) {
            try {
                Account account = (Account) objectInputStream.readObject();
                masterRecords.put(account.getAccountNumber(), account);
            } catch (EOFException e) {
                break;
            }
        }
        objectInputStream.close();
    }

    public void processTransactionFile(String filename) throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = fileReader.readLine()) != null) {
            String[] parts = line.split(",");
            String accountNumber = parts[0].trim();
            double amount = Double.parseDouble(parts[1].trim());

            TransactionRecord transactionRecord = new TransactionRecord(accountNumber, amount);

            if (masterRecords.containsKey(accountNumber)) {
                masterRecords.get(accountNumber).addTransaction(transactionRecord);
            } else {
                logFile.println("Unmatched transaction record for account number " + accountNumber);
            }
        }

        fileReader.close();
    }

    public void writeNewMastFile(String filename) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename));

        for (Account account : masterRecords.values()) {
            objectOutputStream.writeObject(account);
        }

        objectOutputStream.close();
    }

    public void closeLogFile() {
        logFile.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileMatch fileMatch = new FileMatch();

        // Read the old master file
        fileMatch.readOldMastFile("oldmast.ser");

        // Process the transaction file
        fileMatch.processTransactionFile("trans.txt");

        // Write the new master file
        fileMatch.writeNewMastFile("newmast.ser");

        // Close the log file
        fileMatch.closeLogFile();

        System.out.println("File matching completed. Check 'newmast.ser' for updated master records and 'log.txt' for unmatched transactions.");
    }
}

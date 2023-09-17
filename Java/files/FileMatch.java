import java.io.*;
import java.util.HashMap;

class TransactionRecord {
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

class Account {
    private String accountNumber;
    private String name;
    private double balance;

    public Account(String accountNumber, String name, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void combine(TransactionRecord transactionRecord) {
        balance += transactionRecord.getAmount();
    }
}

class FileMatch {
    private HashMap<String, Account> masterRecords = new HashMap<>();
    private PrintWriter logFile;

    public FileMatch() throws FileNotFoundException {
        logFile = new PrintWriter("log.txt");
    }

    public void readOldMastFile(String filename) throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = fileReader.readLine()) != null) {
            String[] parts = line.split(",");
            String accountNumber = parts[0].trim();
            String name = parts[1].trim();
            double balance = Double.parseDouble(parts[2].trim());

            masterRecords.put(accountNumber, new Account(accountNumber, name, balance));
        }

        fileReader.close();
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
                masterRecords.get(accountNumber).combine(transactionRecord);
            } else {
                logFile.println("Unmatched transaction record for account number " + accountNumber);
            }
        }

        fileReader.close();
    }

    public void writeNewMastFile(String filename) throws IOException {
        PrintWriter fileWriter = new PrintWriter(filename);

        for (Account account : masterRecords.values()) {
            fileWriter.println(account.getAccountNumber() + ", " + account.getBalance());
        }

        fileWriter.close();
    }

    public void closeLogFile() {
        logFile.close();
    }

    public static void main(String[] args) throws IOException {
        FileMatch fileMatch = new FileMatch();

        // Read the old master file
        fileMatch.readOldMastFile("oldmast.txt");

        // Process the transaction file
        fileMatch.processTransactionFile("trans.txt");

        // Write the new master file
        fileMatch.writeNewMastFile("newmast.txt");

        // Close the log file
        fileMatch.closeLogFile();

        System.out.println("File matching completed. Check 'newmast.txt' for updated master records and 'log.txt' for unmatched transactions.");
    }
}

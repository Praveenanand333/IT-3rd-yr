// savings


class SavingsAccount {
    private static double annualInterestRate;
    private double savingsBalance;

    public SavingsAccount(double balance) {
        savingsBalance = balance;
    }

    public void calculateMonthlyInterest() {
        double monthlyInterest = (savingsBalance * annualInterestRate) / 12;
        savingsBalance += monthlyInterest;
    }

    public static void modifyInterestRate(double newInterestRate) {
        annualInterestRate = newInterestRate;
    }

    public void displayBalance() {
        System.out.printf("Balance: $%.2f%n", savingsBalance);
    }
}

public class Testsavings {

    public static void main(String[] args) {
        SavingsAccount.modifyInterestRate(0.04);

        SavingsAccount saver1 = new SavingsAccount(2000.00);
        SavingsAccount saver2 = new SavingsAccount(3000.00);

        System.out.println("Initial balances:");
        saver1.displayBalance();
        saver2.displayBalance();
        System.out.println();

        for (int month = 1; month <= 12; month++) {
            saver1.calculateMonthlyInterest();
            saver2.calculateMonthlyInterest();

            System.out.println("Month " + month + " balances:");
            saver1.displayBalance();
            saver2.displayBalance();
            System.out.println();
        }

        SavingsAccount.modifyInterestRate(0.05);

        saver1.calculateMonthlyInterest();
        saver2.calculateMonthlyInterest();

        System.out.println("Next month balances with modified interest rate:");
        saver1.displayBalance();
        saver2.displayBalance();
    }
}

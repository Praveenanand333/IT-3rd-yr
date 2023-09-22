import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class PrimeFactor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a whole number: ");
        int number = scanner.nextInt();

        if (isPrime(number)) {
            System.out.println(number + " is a prime number.");
        } else {
            System.out.println("Unique prime factors of " + number + ": " + getUniquePrimeFactors(number));
        }

        scanner.close();
    }

    private static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
    private static Set<Integer> getUniquePrimeFactors(int n) {
        Set<Integer> primeFactors = new HashSet<>();
        while (n % 2 == 0) {
            primeFactors.add(2);
            n /= 2;
        }
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            while (n % i == 0) {
                primeFactors.add(i);
                n /= i;
            }
        }
        if (n > 1) {
            primeFactors.add(n);
        }
        return primeFactors;
    }
}

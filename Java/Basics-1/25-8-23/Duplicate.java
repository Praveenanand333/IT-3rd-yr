//ques4

import java.util.Scanner;

public class Duplicate {
    public static void main(String[] args) {
        final int ARRAY_SIZE = 5;
        int[] uniqueNumbers = new int[ARRAY_SIZE];
        int uniqueCount = 0;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter five numbers between 10 and 100 (inclusive):");

        while (uniqueCount < ARRAY_SIZE) {
            int inputNumber = scanner.nextInt();

            if (inputNumber >= 10 && inputNumber <= 100) {
                boolean isDuplicate = false;

                for (int i = 0; i < uniqueCount; i++) {
                    if (uniqueNumbers[i] == inputNumber) {
                        isDuplicate = true;
                        break;
                    }
                }

                if (!isDuplicate) {
                    uniqueNumbers[uniqueCount] = inputNumber;
                    uniqueCount++;

                    System.out.println("Unique numbers entered so far:");
                    for (int i = 0; i < uniqueCount; i++) {
                        System.out.print(uniqueNumbers[i] + " ");
                    }
                    System.out.println();
                } else {
                    System.out.println("Duplicate number, try again.");
                }
            } else {
                System.out.println("Number out of range, try again.");
            }
        }

        System.out.println("All unique numbers entered:");
        for (int i = 0; i < uniqueCount; i++) {
            System.out.print(uniqueNumbers[i] + " ");
        }
        System.out.println();
    }
}


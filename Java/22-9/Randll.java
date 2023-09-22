import java.util.LinkedList;
import java.util.Random;

public class Randll {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        Random random = new Random();

        // Insert 25 random integers from 0 to 100 in order
        for (int i = 0; i < 25; i++) {
            int randomNumber = random.nextInt(101); // Generates a random number between 0 and 100
            linkedList.add(randomNumber);
        }

        // Calculate the sum of elements
        int sum = 0;
        for (int number : linkedList) {
            sum += number;
        }

        // Calculate the floating-point average
        double average = (double) sum / linkedList.size();

        // Print the linked list, sum, and average
        System.out.println("Linked List: " + linkedList);
        System.out.println("Sum of Elements: " + sum);
        System.out.println("Average of Elements: " + average);
    }
}

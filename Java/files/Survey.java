import java.util.Formatter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Survey{

    public static void main(String[] args) {
        // Create a Formatter to write to numbers.txt
        try (Formatter formatter = new Formatter("numbers.txt")) {
            Scanner userInput = new Scanner(System.in);
            int response;
            
            System.out.println("Enter survey responses (Enter -1 to finish):");
            
            // Prompt the user for responses until they enter -1
            while (true) {
                System.out.print("Response: ");
                response = userInput.nextInt();
                
                if (response == -1) {
                    break; // Exit the loop if -1 is entered
                }
                
                // Write the response to numbers.txt using format
                formatter.format("%d%n", response);
            }
            
            System.out.println("Survey responses have been saved to numbers.txt.");
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found.");
        }

        // Read survey responses from numbers.txt and write to output.txt
        try (Scanner scanner = new Scanner(new File("numbers.txt"));
             PrintWriter outputWriter = new PrintWriter("output.txt")) {
            
            System.out.println("Reading survey responses from numbers.txt and writing to output.txt:");

            while (scanner.hasNextInt()) {
                int response = scanner.nextInt();
                // Output the response to the console
                System.out.println("Response: " + response);
                // Write the response to output.txt
                outputWriter.println("Response: " + response);
            }

            System.out.println("Survey responses have been written to output.txt.");
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found.");
        }
    }
}

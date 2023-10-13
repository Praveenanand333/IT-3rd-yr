import java.io.*;
public class Filehandlingdemo {
    public static void main(String[] args) {
        String fileName = "demo.txt";
        try {
            // File file = new File(fileName);
            // FileWriter writer = new FileWriter(file);
            BufferedWriter writer=new BufferedWriter(new FileWriter(fileName));
            writer.write("Hello, this is a file handling demonstration in Java.\n");
            writer.write("We are writing data to a file and then reading it back.\n");
            writer.close();
            System.out.println("Data has been written to the file.");
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
        try {
            // FileReader reader = new FileReader(fileName);
            // int character;
           String character;
            BufferedReader reader=new BufferedReader(new FileReader(fileName));
            System.out.println("Data read from the file:");
            while ((character = reader.readLine()) != null) {
                System.out.print(character+"\n");
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("An error occurred while reading from the file: " + e.getMessage());
        }
    }
}

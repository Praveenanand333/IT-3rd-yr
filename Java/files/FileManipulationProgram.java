import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileManipulationProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("File Manipulation Menu:");
            System.out.println("1. List files with a specific extension in a folder");
            System.out.println("2. Check if a file or directory exists");
            System.out.println("3. Check file/directory permissions");
            System.out.println("4. Check if a path is a directory or a file");
            System.out.println("5. Compare two files lexicographically");
            System.out.println("6. Get the last modified date of a file");
            System.out.println("7. Read input from console");
            System.out.println("8. Get file size");
            System.out.println("9. Read file content into a byte array");
            System.out.println("10. Read file content line by line");
            System.out.println("11. Read a plain text file");
            System.out.println("12. Read a file line by line and store it");
            System.out.println("13. Store text file content line by line in an array");
            System.out.println("14. Write and read a plain text file");
            System.out.println("15. Append text to an existing file");
            System.out.println("16. Read the first 3 lines of a file");
            System.out.println("17. Find the longest word in a text file");
            System.out.println("18. List all file/directory names in a directory");
            System.out.println("19. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    listFilesWithExtension(scanner);
                    break;
                case 2:
                    checkFileOrDirectoryExistence(scanner);
                    break;
                case 3:
                    checkPermissions(scanner);
                    break;
                case 4:
                    checkDirectoryOrFile(scanner);
                    break;
                case 5:
                    compareFiles(scanner);
                    break;
                case 6:
                    getLastModifiedDate(scanner);
                    break;
                case 7:
                    readConsoleInput(scanner);
                    break;
                case 8:
                    getFileSize(scanner);
                    break;
                case 9:
                    readFileIntoByteArray(scanner);
                    break;
                case 10:
                    readFileLineByLine(scanner);
                    break;
                case 11:
                    readPlainTextFile(scanner);
                    break;
                case 12:
                    readFileLineByLineAndStore(scanner);
                    break;
                case 13:
                    storeTextFileContentInArray(scanner);
                    break;
                case 14:
                    writeAndReadTextFile(scanner);
                    break;
                case 15:
                    appendTextToFile(scanner);
                    break;
                case 16:
                    readFirstThreeLines(scanner);
                    break;
                case 17:
                    findLongestWordInFile(scanner);
                    break;
                case 18:
                    listFilesAndDirectories(scanner);
                    break;
                case 19:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void listFilesWithExtension(Scanner scanner) {
        System.out.print("Enter folder path: ");
        String folderPath = scanner.nextLine();
        System.out.print("Enter file extension (e.g., '.txt'): ");
        String extension = scanner.nextLine();

        File folder = new File(folderPath);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(extension));

        if (files != null && files.length > 0) {
            System.out.println("Files with extension '" + extension + "' in folder:");
            for (File file : files) {
                System.out.println(file.getName());
            }
        } else {
            System.out.println("No files with extension '" + extension + "' found in the folder.");
        }
    }

    private static void checkFileOrDirectoryExistence(Scanner scanner) {
        System.out.print("Enter file or directory path: ");
        String path = scanner.nextLine();
        File fileOrDir = new File(path);

        if (fileOrDir.exists()) {
            if (fileOrDir.isDirectory()) {
                System.out.println("The specified path is a directory.");
            } else if (fileOrDir.isFile()) {
                System.out.println("The specified path is a file.");
            }
        } else {
            System.out.println("The specified path does not exist.");
        }
    }

    private static void checkPermissions(Scanner scanner) {
        System.out.print("Enter file or directory path: ");
        String path = scanner.nextLine();
        File fileOrDir = new File(path);

        if (fileOrDir.exists()) {
            System.out.println("Read permission: " + fileOrDir.canRead());
            System.out.println("Write permission: " + fileOrDir.canWrite());
        } else {
            System.out.println("The specified path does not exist.");
        }
    }

    private static void checkDirectoryOrFile(Scanner scanner) {
        System.out.print("Enter file or directory path: ");
        String path = scanner.nextLine();
        File fileOrDir = new File(path);

        if (fileOrDir.exists()) {
            if (fileOrDir.isDirectory()) {
                System.out.println("The specified path is a directory.");
            } else if (fileOrDir.isFile()) {
                System.out.println("The specified path is a file.");
            }
        } else {
            System.out.println("The specified path does not exist.");
        }
    }

    private static void compareFiles(Scanner scanner) {
        System.out.print("Enter path of the first file: ");
        String filePath1 = scanner.nextLine();
        System.out.print("Enter path of the second file: ");
        String filePath2 = scanner.nextLine();

        try {
            byte[] file1Contents = Files.readAllBytes(Paths.get(filePath1));
            byte[] file2Contents = Files.readAllBytes(Paths.get(filePath2));

            if (Arrays.equals(file1Contents, file2Contents)) {
                System.out.println("Files are equal.");
            } else {
                System.out.println("Files are not equal.");
            }
        } catch (IOException e) {
            System.err.println("An error occurred while comparing files: " + e.getMessage());
        }
    }

    private static void getLastModifiedDate(Scanner scanner) {
        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();
        File file = new File(filePath);

        if (file.exists()) {
            long lastModified = file.lastModified();
            Date date = new Date(lastModified);
            System.out.println("Last modified date: " + date);
        } else {
            System.out.println("The specified file does not exist.");
        }
    }

    private static void readConsoleInput(Scanner scanner) {
        System.out.print("Enter a line of text: ");
        String input = scanner.nextLine();
        System.out.println("You entered: " + input);
    }

    private static void getFileSize(Scanner scanner) {
        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();
        File file = new File(filePath);

        if (file.exists() && file.isFile()) {
            long fileSizeBytes = file.length();
            double fileSizeKB = (double) fileSizeBytes / 1024;
            double fileSizeMB = fileSizeKB / 1024;
            System.out.println("File size (bytes): " + fileSizeBytes);
            System.out.println("File size (KB): " + fileSizeKB);
            System.out.println("File size (MB): " + fileSizeMB);
        } else {
            System.out.println("The specified file does not exist.");
        }
    }

    private static void readFileIntoByteArray(Scanner scanner) {
        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();

        try {
            byte[] fileContents = Files.readAllBytes(Paths.get(filePath));
            System.out.println("File content as byte array: " + Arrays.toString(fileContents));
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    private static void readFileLineByLine(Scanner scanner) {
        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    private static void readPlainTextFile(Scanner scanner) {
        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            System.out.println("File content:\n" + sb.toString());
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    private static void readFileLineByLineAndStore(Scanner scanner) {
        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            System.out.println("File content stored in a list:");
            for (String storedLine : lines) {
                System.out.println(storedLine);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    private static void storeTextFileContentInArray(Scanner scanner) {
        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            String[] linesArray = lines.toArray(new String[0]);
            System.out.println("File content stored in an array:");
            for (String storedLine : linesArray) {
                System.out.println(storedLine);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    private static void writeAndReadTextFile(Scanner scanner) {
        System.out.print("Enter file path for writing: ");
        String filePath = scanner.nextLine();
        System.out.print("Enter text to write to the file: ");
        String textToWrite = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(textToWrite);
            System.out.println("Text has been written to the file.");
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }

        System.out.println("Reading the contents of the written file:");
        readFileLineByLine(scanner);
    }

    private static void appendTextToFile(Scanner scanner) {
        System.out.print("Enter file path for appending: ");
        String filePath = scanner.nextLine();
        System.out.print("Enter text to append to the file: ");
        String textToAppend = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(textToAppend);
            System.out.println("Text has been appended to the file.");
        } catch (IOException e) {
            System.err.println("An error occurred while appending to the file: " + e.getMessage());
        }
    }

    private static void readFirstThreeLines(Scanner scanner) {
        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineCount = 0;
            while ((line = reader.readLine()) != null && lineCount < 3) {
                System.out.println(line);
                lineCount++;
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    private static void findLongestWordInFile(Scanner scanner) {
        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            String longestWord = "";
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (word.length() > longestWord.length()) {
                        longestWord = word;
                    }
                }
            }
            System.out.println("Longest word in the file: " + longestWord);
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    private static void listFilesAndDirectories(Scanner scanner) {
        System.out.print("Enter directory path: ");
        String directoryPath = scanner.nextLine();
        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            String[] filesAndDirs = directory.list();
            if (filesAndDirs != null) {
                System.out.println("Files and directories in the specified directory:");
                for (String name : filesAndDirs) {
                    System.out.println(name);
                }
            }
        } else {
            System.out.println("The specified directory does not exist.");
        }
    }
}
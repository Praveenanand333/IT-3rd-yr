import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Game {
    private int randomNumber;
    private JFrame frame;
    private JTextField guessField;
    private JButton guessButton;
    private JLabel feedbackLabel;
    private JLabel promptLabel;

    public Game() {
        initializeGame();
        generateRandomNumber();
    }

    private void initializeGame() {
        frame = new JFrame("Guess the Number Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        promptLabel = new JLabel("I have a number between 1 and 1000. Can you guess my number?");
        guessField = new JTextField(10);
        guessButton = new JButton("Guess");
        feedbackLabel = new JLabel();

        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        frame.add(promptLabel);
        frame.add(guessField);
        frame.add(guessButton);
        frame.add(feedbackLabel);

        frame.setSize(400, 150);
        frame.setVisible(true);
    }

    private void generateRandomNumber() {
        Random rand = new Random();
        randomNumber = rand.nextInt(1000) + 1;
    }

    private void checkGuess() {
        try {
            int userGuess = Integer.parseInt(guessField.getText());

            if (userGuess < randomNumber) {
                feedbackLabel.setText("Too Low");
                guessField.setBackground(Color.BLUE);
            } else if (userGuess > randomNumber) {
                feedbackLabel.setText("Too High");
                guessField.setBackground(Color.RED);
            } else {
                feedbackLabel.setText("Correct!");
                guessField.setBackground(Color.GREEN);
                guessField.setEditable(false);
            }
        } catch (NumberFormatException ex) {
            feedbackLabel.setText("Invalid input. Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Game();
            }
        });
    }
}

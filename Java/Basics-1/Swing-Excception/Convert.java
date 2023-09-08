import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Convert {
    public static void main(String[] args) {
        JFrame frame = new JFrame(" Fahrenheit to  Celsius Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        JPanel panel = new JPanel();

        JLabel celsiusLabel = new JLabel("Enter Fahrenheit:");
        JTextField celsiusTextField = new JTextField(10);
        JLabel resultLabel = new JLabel("Result in Celsius:");

        JButton convertButton = new JButton("Convert");

        panel.add(celsiusLabel);
        panel.add(celsiusTextField);
        panel.add(resultLabel);
        panel.add(convertButton);

        frame.add(panel);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double fahrenheit= Double.parseDouble(celsiusTextField.getText());
                    double celsius = (fahrenheit-32) *(5/9.0);
                    resultLabel.setText("Result in Fahrenheit: " + celsius);
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Invalid input. Please enter a valid number.");
                }
            }
        });

        frame.setVisible(true);
    }
}

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Converter {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Temperature Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();

        JLabel inputLabel = new JLabel("Enter Temperature:");
        JTextField inputTextField = new JTextField(10);
        JLabel resultLabel = new JLabel("Result:");

        JComboBox<String> fromComboBox = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        JComboBox<String> toComboBox = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});

        JButton convertButton = new JButton("Convert");

        panel.add(inputLabel);
        panel.add(inputTextField);
        panel.add(fromComboBox);
        panel.add(resultLabel);
        panel.add(toComboBox);
        panel.add(convertButton);

        frame.add(panel);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double inputTemp = Double.parseDouble(inputTextField.getText());
                    String fromUnit = (String) fromComboBox.getSelectedItem();
                    String toUnit = (String) toComboBox.getSelectedItem();
                    double resultTemp = convertTemperature(inputTemp, fromUnit, toUnit);
                    resultLabel.setText("Result: " + resultTemp + " " + toUnit);
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Invalid input. Please enter a valid number.");
                }
            }
        });

        frame.setVisible(true);
    }

    private static double convertTemperature(double value, String fromUnit, String toUnit) {
        if (fromUnit.equals(toUnit)) {
            return value;
        } else if (fromUnit.equals("Celsius")) {
            if (toUnit.equals("Fahrenheit")) {
                return (value * 9/5) + 32;
            } else if (toUnit.equals("Kelvin")) {
                return value + 273.15;
            }
        } else if (fromUnit.equals("Fahrenheit")) {
            if (toUnit.equals("Celsius")) {
                return (value - 32) * 5/9;
            } else if (toUnit.equals("Kelvin")) {
                return (value - 32) * 5/9 + 273.15;
            }
        } else if (fromUnit.equals("Kelvin")) {
            if (toUnit.equals("Celsius")) {
                return value - 273.15;
            } else if (toUnit.equals("Fahrenheit")) {
                return (value - 273.15) * 9/5 + 32;
            }
        }
        return 0.0; // Default to 0 if conversion not supported
    }
}

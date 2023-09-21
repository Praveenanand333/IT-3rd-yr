import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

class MyShape implements Serializable {
    private int x, y, width, height;
    private Color color;

    public MyShape(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
}

public class ShapeSaverLoader extends JFrame {
    private ArrayList<MyShape> shapes = new ArrayList<>();

    public ShapeSaverLoader() {
        setTitle("Shape Saver and Loader");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int choice = fileChooser.showSaveDialog(ShapeSaverLoader.this);

                if (choice == JFileChooser.APPROVE_OPTION) {
                    try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileChooser.getSelectedFile()))) {
                        outputStream.writeObject(shapes);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int choice = fileChooser.showOpenDialog(ShapeSaverLoader.this);

                if (choice == JFileChooser.APPROVE_OPTION) {
                    try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileChooser.getSelectedFile()))) {
                        shapes = (ArrayList<MyShape>) inputStream.readObject();
                        repaint(); // Repaint the window to show loaded shapes
                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Your code to draw and manipulate MyShape objects on the screen here
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                int width = 30;
                int height = 30;
                Color color = Color.BLUE;

                MyShape shape = new MyShape(x, y, width, height, color);
                shapes.add(shape);
                repaint();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (MyShape shape : shapes) {
            shape.draw(g);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ShapeSaverLoader().setVisible(true);
            }
        });
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BouncingBallApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Bouncing Ball");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            BouncingBallPanel panel = new BouncingBallPanel();
            frame.add(panel);
            frame.pack();
            frame.setVisible(true);
        });
    }
}

class BouncingBallPanel extends JPanel {
    private int ballSize = 50;
    private int x = 0, y = 0;
    private int xSpeed = 2, ySpeed = 2;
    private Thread animationThread;

    public BouncingBallPanel() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startAnimation();
            }
        });
    }

    private void startAnimation() {
        if (animationThread == null || !animationThread.isAlive()) {
            animationThread = new Thread(() -> {
                while (true) {
                    moveBall();
                    repaint();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            animationThread.setPriority(Thread.NORM_PRIORITY);
            animationThread.start();
        }
    }

    private void moveBall() {
        x += xSpeed;
        y += ySpeed;

        // Check for collision with panel boundaries
        if (x + ballSize > getWidth() || x < 0) {
            xSpeed = -xSpeed; // Reverse direction on horizontal collision
        }
        if (y + ballSize > getHeight() || y < 0) {
            ySpeed = -ySpeed; // Reverse direction on vertical collision
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillOval(x, y, ballSize, ballSize);
    }
}

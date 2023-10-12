import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultiBouncingBallsApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Bouncing Balls");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            MultiBouncingBallsPanel panel = new MultiBouncingBallsPanel();
            frame.add(panel);
            frame.pack();
            frame.setVisible(true);
        });
    }
}

class Ball {
    private int x, y;
    private int xSpeed, ySpeed;
    private int ballSize;
    private Color color;

    public Ball(int x, int y, int xSpeed, int ySpeed, int ballSize, Color color) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.ballSize = ballSize;
        this.color = color;
    }

    public void move() {
        x += xSpeed;
        y += ySpeed;
    }

    public void reverseXDirection() {
        xSpeed = -xSpeed;
    }

    public void reverseYDirection() {
        ySpeed = -ySpeed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getBallSize() {
        return ballSize;
    }

    public Color getColor() {
        return color;
    }
}

class MultiBouncingBallsPanel extends JPanel {
    private List<Ball> balls = new ArrayList<>();

    public MultiBouncingBallsPanel() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                addBall(e.getX(), e.getY());
            }
        });

        startAnimation();
    }

    private void startAnimation() {
        Thread animationThread = new Thread(() -> {
            while (true) {
                moveBalls();
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

    private void moveBalls() {
        for (Ball ball : balls) {
            ball.move();
            checkCollision(ball);
        }
    }

    private void checkCollision(Ball ball) {
        int x = ball.getX();
        int y = ball.getY();
        int size = ball.getBallSize();

        if (x + size > getWidth() || x < 0) {
            ball.reverseXDirection();
        }
        if (y + size > getHeight() || y < 0) {
            ball.reverseYDirection();
        }
    }

    private void addBall(int x, int y) {
        if (balls.size() >= 20) {
            return; // Limit to a maximum of 20 balls
        }

        int xSpeed = new Random().nextInt(5) + 1; // Random speed between 1 and 5
        int ySpeed = new Random().nextInt(5) + 1; // Random speed between 1 and 5
        int ballSize = new Random().nextInt(30) + 20; // Random size between 20 and 50
        Color color = getRandomColor();

        balls.add(new Ball(x, y, xSpeed, ySpeed, ballSize, color));
    }

    private Color getRandomColor() {
        Random rand = new Random();
        return new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Ball ball : balls) {
            g.setColor(ball.getColor());
            g.fillOval(ball.getX(), ball.getY(), ball.getBallSize(), ball.getBallSize());
        }
    }
}

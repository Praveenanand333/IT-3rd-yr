import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultiBouncingBallsWithShadowsApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Bouncing Balls with Shadows");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            MultiBouncingBallsWithShadowsPanel panel = new MultiBouncingBallsWithShadowsPanel();
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
    private boolean isGrowing = true;

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

        if (x + ballSize > MultiBouncingBallsWithShadowsPanel.WIDTH || x < 0) {
            xSpeed = -xSpeed;
            isGrowing = !isGrowing;
        }

        if (y + ballSize > MultiBouncingBallsWithShadowsPanel.HEIGHT || y < 0) {
            ySpeed = -ySpeed;
            isGrowing = !isGrowing;
        }
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

    public boolean isGrowing() {
        return isGrowing;
    }
}

class MultiBouncingBallsWithShadowsPanel extends JPanel {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private List<Ball> balls = new ArrayList<>();

    public MultiBouncingBallsWithShadowsPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

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
            if (ball.isGrowing()) {
                g.fillOval(ball.getX(), ball.getY(), ball.getBallSize(), ball.getBallSize());
            } else {
                g.fillOval(ball.getX(), ball.getY(), ball.getBallSize(), ball.getBallSize());
                g.setColor(Color.BLACK);
                g.fillOval(ball.getX() + 5, getHeight() - 20, ball.getBallSize() - 10, 10);
            }
        }
    }
}

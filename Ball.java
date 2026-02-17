import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends Circle {
    // Variables for ball start and moving position
    // The dx, dy represent changes in x per frame
    // JavaFX tries to play at 60 FPS
    private double dx;
    private double dy;
    private double start_x;
    private double start_y;

    // Constructor for ball initialization
    // Takes x, y coordinates and radius
    public Ball(double x, double y, double s) {
        super(x, y, s);
        this.dx = 0;
        this.dy = 0;
        this.start_x = x;
        this.start_y = y;
        super.setStroke(Color.WHITE);

    }

    // Get direction of ball movement
    public Direction getDirection() {
        if (dx > 0) {
            return Direction.RIGHT;
        } else if (dx < 0) {
            return Direction.LEFT;
        } else {
            return Direction.NONE;
        }
    }

    // Get speed of ball
    public double getSpeed() {
        return dx;
    }

    // Start ball's movement
    public void start() {
        dx = (Math.random() - 0.5) * 4;
        dx = dx >= 0 ? dx + 2 : dx - 2;
        dy = (Math.random() - 0.5) * 4;
    }

    // Stop the ball's movement
    public void stop() {
        dx = 0;
        dy = 0;
    }

    // Move ball by velocity
    public void move() {
        setCenterX(getCenterX() + dx);
        setCenterY(getCenterY() + dy);
    }

    // Action for collision with paddle
    public void bounceX() {
        // We flip the horizontal component
        dx = -dx;
    }

    // Action for collision with wall
    public void bounceY() {
        // We flip the vertical component
        dy = -dy;
    }

    // Reset ball to starting position and stop movement
    public void reset() {
        setCenterX(start_x);
        setCenterY(start_y);
        dx = 0;
        dy = 0;
    }
}

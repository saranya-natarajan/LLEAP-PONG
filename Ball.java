package Pong;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/*
 * This file implements a bouncing ball
 */
public class Ball extends Circle {
    private double dx; // dx and dy are the deltas that the ball will move each frame
    private double dy; // JavaFX tries to play at 60 FPS
    private double start_x; // Storing the initial position
    private double start_y;
    private double gravity;

    // Constructor for ball initialization
    // Takes X, Y coordinates and radius
    public Ball(double x, double y, double r) {
        super(x, y, r);
        this.dx = 0;
        this.dy = 0;
        this.start_x = x;
        this.start_y = y;
        this.gravity = 0;
        super.setStroke(Color.rgb(0x80, 0xa0, 0xa0));
        super.setFill(Color.rgb(0x50, 0x80, 0xa0));
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

    // Get the length of the speed vector
    public double getSpeed() {
        return Math.sqrt(dx * dx + dy * dy);
    }

    // Get the horizontal speed component. Note that the speed includes a direction.
    // Positive values are moving right, negative values are moving left.
    public double getSpeedX() {
        return dx;
    }

    public void setGravity(double g) {
        gravity = g;
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

    // Move the ball. Called once per frame.
    public void move() {
        dy += gravity;
        setCenterX(getCenterX() + dx);
        setCenterY(getCenterY() + dy);
    }

    // Modify the speed vector length
    public void adjustSpeed(double addend) {
        double factor = addend / getSpeed(); // Calculate the percentual difference
        dx *= (factor + 1);
        dy *= (factor + 1);
    }

    // Action for collision with pad
    public void bounceX(double angle) {
        Direction d = getDirection();
        // To preserve the speed we calculate the vector length of (dx,dy)
        // and use that to calculate the new X and Y components given the angle.
        double speed = getSpeed();
        dx = speed * Math.cos(angle);
        dy = speed * Math.sin(angle);
        if (d == Direction.RIGHT) {
            dx = -dx;
        }
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
        gravity = 0;
    }

    public String toString() {
        return "[dx: " + dx + " dy: " + dy + "]";
    }
}

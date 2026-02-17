package Pong;

import Pong.Ball;
import Pong.Direction;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Pad extends Rectangle {
    // The constant speed at which the ball moves
    private double speed;
    // The direction, 0 for standing still
    private int direction;
    private int lower_limit;
    private int upper_limit;
    private double start_y;

    // Paddle Initialization
    // Takes the x coordinate and upper, lower limits
    public Pad(double x, int ll, int ul) {
        super(x, (ul - ll + 60) / 2, 10, 60);
        this.speed = 2;
        this.direction = 0;
        this.lower_limit = ll;
        this.upper_limit = ul;
        // Start in the middle
        this.start_y = (ul - ll + 60) / 2;
        super.setStroke(Color.rgb(255, 255, 255));
    }

    // Set paddle direction
    public void setDirection(Direction d) {
        switch(d) {
            case Direction.UP:
                direction = -1;
                break;
            case Direction.DOWN:
                direction = 1;
                break;
        }
    }

    // Move paddle within the limit
    public void move() {
        setY(getY() + (speed * direction));
        if (getY() < lower_limit) {
            setY(lower_limit);
        }
        if (getY() > upper_limit - getHeight()) {
            setY(upper_limit - getHeight());
        }
    }

    // Paddle and ball collision
    public boolean within(Ball b) {
        double ball_left_side = b.getCenterX() - b.getRadius();
        double ball_right_side = b.getCenterX() + b.getRadius();
        double pad_left_side = getX();
        double pad_right_side = getX() + getWidth();

        // Check if the y coordinate is within the paddle's current y range
        boolean isYInRange = b.getCenterY() >= getY() && b.getCenterY() <= getY() + getHeight();
        // Different conditions depending on the side of the paddle that the ball is on
        boolean rightSide = b.getDirection() == Direction.LEFT &&
                            // Ball is left of the paddle boundary
                            ball_left_side <= pad_right_side &&
                            // Ball isn't completely over the right paddle boundary
                            ball_left_side >= pad_right_side + b.getSpeed();
        boolean leftSide  = b.getDirection() == Direction.RIGHT &&
                            // Ball is right of the paddle boundary
                            ball_right_side >= pad_left_side &&
                            // Ball isn't completely over the left paddle boundary
                            ball_right_side <= pad_left_side + b.getSpeed();

        return isYInRange && (rightSide || leftSide);
    }

    // Reset to initial values
    public void reset() {
        setY(start_y);
        direction = 0;
    }
}

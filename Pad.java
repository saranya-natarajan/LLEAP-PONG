package Pong;

import Pong.Ball;
import Pong.Direction;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
 * This file implements a pong pad that can move up and down.
 */
public class Pad extends Rectangle {
    private double speed;    // The speed at which the ball moves
    private int direction;   // The direction, 0 for standing still
    private int lower_limit; // The lowest position of the pad
    private int upper_limit; // The highest position of the pad
    private double start_y;  // The initial Y position of the pad

    // Pad initialization
    // Takes the X coordinate, and lower and upper limits
    public Pad(double x, int ll, int ul) {
        // 60 is the height of the pad. Unfortunately we can't yet define a variable here.
        super(x, (ul - ll + 60) / 2, 10, 60);
        this.speed = 2;
        this.direction = 0;
        this.lower_limit = ll;
        this.upper_limit = ul;
        this.start_y = (ul - ll + 60) / 2;  // Start in the middle
        super.setStroke(Color.rgb(255, 255, 255));
    }

    // Set pad direction
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

    // Move pad within the limit
    public void move() {
        setY(getY() + (speed * direction));
        if (getY() < lower_limit) {
            setY(lower_limit);
        }
        if (getY() > upper_limit - getHeight()) {
            setY(upper_limit - getHeight());
        }
    }

    // Pad and ball collision
    public boolean within(Ball b) {
        double ball_left_side = b.getCenterX() - b.getRadius();
        double ball_right_side = b.getCenterX() + b.getRadius();
        double pad_left_side = getX();
        double pad_right_side = getX() + getWidth();

        // Check if the Y coordinate is within the pad's current Y range
        boolean isYInRange = b.getCenterY() >= getY() && b.getCenterY() <= getY() + getHeight();
        // Different conditions depending on which side of the court the pad is on
        boolean rightSide = b.getDirection() == Direction.LEFT &&
                            // Ball is left of the pad boundary
                            ball_left_side <= pad_right_side &&
                            // Ball is within one speed unit from the pad's left side.
                            // We need to check this to avoid "catching" the ball if the
                            // pad moves in height with the ball after the ball has passed.
                            ball_left_side >= pad_right_side + b.getSpeed();
        boolean leftSide  = b.getDirection() == Direction.RIGHT &&
                            // Ball is right of the pad boundary
                            ball_right_side >= pad_left_side &&
                            // Same comment as in the rightSide case
                            ball_right_side <= pad_left_side + b.getSpeed();

        return isYInRange && (rightSide || leftSide);
    }

    // Reset to initial values
    public void reset() {
        setY(start_y);
        direction = 0;
    }
}

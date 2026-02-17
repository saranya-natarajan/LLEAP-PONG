package Pong;

import Pong.Ball;
import Pong.Direction;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Pad extends Rectangle {
    private double speed;
    private int direction;
    private int lower_limit;
    private int upper_limit;
    private double start_y;

    // Paddle Initialization
    public Pad(double x, int ll, int ul) {
        super(x, (ul - ll + 60) / 2, 10, 60);
        this.speed = 2;
        this.direction = 0;
        this.lower_limit = ll;
        this.upper_limit = ul;
        this.start_y = (ul - ll + 60) / 2;
        super.setStroke(Color.rgb(255, 255, 255));
    }

    // Set paddle direction
    public void setDirection(Direction d) {
        switch(d) {
            case Direction.UP: direction = -1; break;
            case Direction.DOWN: direction = 1; break;
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

        return (b.getCenterY() >= getY() && b.getCenterY() <= getY() + getHeight()) &&
                ((b.getDirection() == Direction.LEFT &&
                  ball_left_side <= pad_right_side &&
                  ball_left_side >= pad_right_side + b.getSpeed()) ||
                 (b.getDirection() == Direction.RIGHT &&
                  ball_right_side >= pad_left_side &&
                  ball_right_side <= pad_left_side + b.getSpeed()));
    }

    // Reset to initial values
    public void reset() {
        setY(start_y);
        direction = 0;
    }
}

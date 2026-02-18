package Pong;

import Pong.Direction;
import Pong.Pad;

/*
 * This file implements a player with a pad and a getScore
 */
public class Player {
    Pad pad;   // Player's pad
    int score; // Player's score

    // Player Initialization
    public Player(double x_pos, int range, int offset) {
        this.score = 0;
        this.pad = new Pad(x_pos, offset, range + offset);
    }

    // Get player's pad
    public Pad getPad() {
        return pad;
    }

    // Get player's score
    public int getScore() {
        return this.score;
    }

    // Increase player's score
    public void score() {
        score++;
    }

    // Set the direction of the pad to go up
    public void goUp() {
        pad.setDirection(Direction.UP);
    }

    // Set the direction of the pad to go down
    public void goDown() {
        pad.setDirection(Direction.DOWN);
    }

    // Reset to initial values
    public void reset() {
        pad.reset();
    }
}

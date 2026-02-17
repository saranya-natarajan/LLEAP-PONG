public class Player {
    Pad pad; // Player's pad
    int score; // Player's score

    // Player Initialization
    public Player(double x_pos, int range, int offset) {
        this.score = 0;
        this.pad = new Pad(x_pos, offset, range + offset);
    }

    // Get player's pad position
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

    // Move paddle up
    public void goUp() {
        pad.setDirection(Direction.UP);
    }

    // Move paddle down
    public void goDown() {
        pad.setDirection(Direction.DOWN);
    }

    // Reset paddle
    public void reset() {
        pad.reset();
    }
}

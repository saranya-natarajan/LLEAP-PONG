package Pong;

import Pong.Direction;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/*
 * To build and run, see the included README file
 */
public class Pong extends Application {
    public static void main(String[] args) {
        // Tells JavaFX to handle the launching.
        // When using JavaFX, the "start" method is where we write the program.
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Size of Pong window and margins in pixels.
        // The grid starts with the origin (0, 0) in the upper left, e.g.:
        // *-----*-----*-----*-----*-----* . . .
        // |(0,0)|(1,0)|(2,0)|(3,0)|(4,0)|
        // *-----*-----*-----*-----*-----*
        // |(0,1)|(1,1)|(2,1)|(3,1)|(4,1)|
        // *-----*-----*-----*-----*-----*
        // |(0,2)|(1,2)|(2,2)|(3,2)|(4,2)|
        // *-----*-----*-----*-----*-----*
        // .
        // .
        // .
        final int WIDTH = 800;
        final int HEIGHT = 600;
        final int MIDDLE = WIDTH / 2;
        final int MARGIN = 50; // Needed to display the score and aesthetics

        // Position of pad
        double p1_position = MARGIN;
        double p2_position = WIDTH - p1_position;

        // Setting up the JavaFX application
        Group root = new Group();
        Scene scene = new Scene(root, WIDTH, HEIGHT, Color.BLACK);
        primaryStage.setScene(scene);

        // Line positions and their properties
        Line middle_line = new Line(MIDDLE, 0, MIDDLE, HEIGHT);
        middle_line.setStroke(Color.rgb(100, 100, 100));
        Line court_upper = new Line(MARGIN, MARGIN, WIDTH - MARGIN, MARGIN);
        Line court_lower = new Line(MARGIN, HEIGHT - MARGIN, WIDTH - MARGIN, HEIGHT - MARGIN);
        court_upper.setStroke(Color.rgb(255, 255, 255));
        court_lower.setStroke(Color.rgb(255, 255, 255));

        // Create two players based on the Player class
        Player player1 = new Player(p1_position, HEIGHT - (2 * MARGIN), MARGIN);
        Player player2 = new Player(p2_position, HEIGHT - (2 * MARGIN), MARGIN);

        // Create a ball
        Ball ball = new Ball(MIDDLE, HEIGHT / 2, 6);

        // Create a message display and initial message
        Text message = new Text(100, 30, "Press space to start");
        message.setFill(Color.rgb(255, 255, 255));
        message.setFont(new Font(28));

        // Create the score display
        Text score = new Text(15, 30, player1.getScore() + " - " + player2.getScore());
        score.setFill(Color.rgb(255, 255, 255));
        score.setFont(new Font(28));

        // Setting up the board
        root.getChildren().add(middle_line);
        root.getChildren().add(court_upper);
        root.getChildren().add(court_lower);
        root.getChildren().add(message);
        root.getChildren().add(score);
        root.getChildren().add(player1.getPad());
        root.getChildren().add(player2.getPad());
        root.getChildren().add(ball);

        primaryStage.show();

        // Setting keyboard controls
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            // This can be extended with more keys by adding more case statements
            switch (key.getCode()) {
                case KeyCode.W:
                    player1.goUp();
                    break;
                case KeyCode.S:
                    player1.goDown();
                    break;
                case KeyCode.UP:
                    player2.goUp();
                    break;
                case KeyCode.DOWN:
                    player2.goDown();
                    break;
                case KeyCode.SPACE:
                    ball.start();
                    // Clear the "press space to start" message
                    message.setText("");
                    break;
                case KeyCode.ESCAPE:
                    ball.stop();
                    break;
            }
        });

        // This animation timer is used to implement behaviour at every frame.
        // The handle method is called once every frame, and is provided the
        // current time in nanoseconds. JavaFX should run at around 60 FPS so
        // handle(time) will be called approximately 60 times a second. There
        // is no guaraintee though, so don't build mission critical services
        // on this assumption.
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Pad and ball movement
                player1.getPad().move();
                player2.getPad().move();
                ball.move();

                // Ball collision with pads
                if (player1.getPad().within(ball) || player2.getPad().within(ball)) {
                    // We simply flip the direction of travel, so we don't need to
                    // track which pad was the one that hit it
                    ball.bounceX();
                }
                // Ball collision with walls.
                // Here, we have to do different things depending on which wall we hit.
                if (ball.getCenterY() - ball.getRadius() <= MARGIN) {
                    // Top wall
                    double diff = MARGIN - (ball.getCenterY() - ball.getRadius());
                    ball.setCenterY(MARGIN + diff + ball.getRadius());
                    ball.bounceY();
                } else if (ball.getCenterY() + ball.getRadius() >= HEIGHT - MARGIN) {
                    // Bottom wall
                    double diff = ball.getCenterY() + ball.getRadius() - (HEIGHT - MARGIN);
                    ball.setCenterY(HEIGHT - MARGIN - diff - ball.getRadius());
                    ball.bounceY();
                }
                // Update score
                if (ball.getCenterX() < 0) {
                    player2.score();
                    reset();
                } else if (ball.getCenterX() > WIDTH) {
                    player1.score();
                    reset();
                }
            }

            // Reset the game to initial values
            private void reset() {
                score.setText(player1.getScore() + " - " + player2.getScore());
                message.setText("Press space to start");
                player1.reset();
                player2.reset();
                ball.reset();
            }
        };

        // Tells the animation timer to start running
        gameLoop.start();
    }
}

package Pong;

import Pong.Direction;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/*
 * To build and run, see the included README file
 */
public class Pong extends Application {
    enum GameState {
        STOP, RUN
    }

    GameState game_state = GameState.STOP;

    private void setGameState(GameState state) {
        game_state = state;
    }

    enum Area {
        NONE, GRAVITY, WIND
    }

    Area active_area = Area.NONE;

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
        Stop[] stops = { new Stop(0, Color.rgb(0x80, 0xa0, 0xff)), new Stop(1, Color.rgb(0x80, 0xff, 0xa0))};
        RadialGradient background = new RadialGradient(0.25, 0.5, 100, 100, 500, false, CycleMethod.NO_CYCLE, stops);
        scene.setFill(background);
        primaryStage.setScene(scene);

        // Line positions and their properties
        Line middle_line = new Line(MIDDLE, 0, MIDDLE, HEIGHT);
        middle_line.setStroke(Color.rgb(0x80, 0x80, 0x80));
        Line court_upper = new Line(MARGIN, MARGIN, WIDTH - MARGIN, MARGIN);
        Line court_lower = new Line(MARGIN, HEIGHT - MARGIN, WIDTH - MARGIN, HEIGHT - MARGIN);
        court_upper.setStroke(Color.rgb(0x20, 0x20, 0x20));
        court_lower.setStroke(Color.rgb(0x20, 0x20, 0x20));

        // Create two players based on the Player class
        Player player1 = new Player(p1_position, HEIGHT - (2 * MARGIN), MARGIN);
        Player player2 = new Player(p2_position, HEIGHT - (2 * MARGIN), MARGIN);

        // Create a ball
        Ball ball = new Ball(MIDDLE, HEIGHT / 2, 6);

        Rectangle lottery_area = new Rectangle(0, HEIGHT - MARGIN, 100, 5);
        lottery_area.setVisible(false);

        // Create a message display and initial message
        Text message = new Text(100, 30, "Press space to start");
        message.setFill(Color.BLACK);
        message.setFont(new Font(28));

        // Create the score display
        Text score = new Text(15, 30, player1.getScore() + " - " + player2.getScore());
        score.setFill(Color.BLACK);
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
        root.getChildren().add(lottery_area);

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
                    if (game_state == GameState.STOP) {
                        setGameState(GameState.RUN);
                        ball.start();
                        message.setText(""); // Clear the "press space to start" message
                    }
                    break;
                case KeyCode.ESCAPE:
                    setGameState(GameState.STOP);
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
                // Only do stuff if the game is active
                if (game_state == GameState.RUN) {
                    // Pad and ball movement
                    player1.getPad().move();
                    player2.getPad().move();
                    ball.move();

                    // Ball collision with pads
                    Pad hit = null;
                    if (player1.getPad().within(ball)) {
                        hit = player1.getPad();
                    } else if (player2.getPad().within(ball)) {
                        hit = player2.getPad();
                    }
                    if (hit != null) {
                        double distance = hit.getCenterDistance(ball.getCenterY());
                        ball.bounceX(distance * Math.PI / 2);
                        // After the angle changed, dx might be smaller than before.
                        // To avoid the ball getting caught in the pad again the next frame,
                        // move the ball out of the pad.
                        while (hit.within(ball)) {
                            ball.move();
                        }
                    }

                    // Ball collision with walls.
                    if (ball.getCenterY() - ball.getRadius() <= MARGIN) {
                        // Top wall
                        double diff = MARGIN - (ball.getCenterY() - ball.getRadius());
                        ball.setCenterY(MARGIN + diff + ball.getRadius());
                        ball.bounceY();
                    } else if (ball.getCenterY() + ball.getRadius() >= HEIGHT - MARGIN) {
                        // Bottom wall
                        if (active_area != Area.NONE && ball.getCenterX() > lottery_area.getX() && ball.getCenterX() < lottery_area.getX() + lottery_area.getWidth()) {
                            switch (active_area) {
                                case Area.GRAVITY:
                                    ball.setGravity(0.035);
                                    break;
                                case Area.WIND:
                                    ball.activateWind();
                                    break;
                            }
                            inactivateArea();
                        }
                        double diff = ball.getCenterY() + ball.getRadius() - (HEIGHT - MARGIN);
                        ball.setCenterY(HEIGHT - MARGIN - diff - ball.getRadius());
                        ball.bounceY();
                    }

                    ball.adjustSpeed(0.001);

                    if (active_area == Area.NONE) {
                        double lottery = Math.random();
                        if (lottery < 0.001) {
                            activateArea(Area.GRAVITY, Color.GREEN);
                        } else if (lottery < 0.02) {
                            activateArea(Area.WIND, Color.BLUE);
                        }
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
            }

            private void activateArea(Area a, Color c) {
                active_area = a;
                lottery_area.setX(Math.random() * (WIDTH - (2 * MARGIN) - lottery_area.getWidth() + MARGIN));
                lottery_area.setFill(c);
                lottery_area.setVisible(true);
            }

            private void inactivateArea() {
                active_area = Area.NONE;
                lottery_area.setVisible(false);
            }

            // Reset the game to initial values
            private void reset() {
                setGameState(GameState.STOP);
                score.setText(player1.getScore() + " - " + player2.getScore());
                message.setText("Press space to start");
                player1.reset();
                player2.reset();
                ball.reset();
                inactivateArea();
            }
        };

        // Tells the animation timer to start running
        gameLoop.start();
    }
}

# Introduction

This document is designed to guide you in enhancing your Pong game through a series of hints. Rather than providing ready-made code solutions, we encourage you to engage deeply with the material and strengthen your problem-solving skills by working through these challenges.

The document is divided into two sections: general hints and specific hints. We recommend that you begin with the general hints to develop your own ideas and strategies, then consult the specific hints if you need more guidance as you implement your improvements.

**Note that the hints here illustrate one way to implement the suggested improvements but there are many other ways to do the same.**

### Happy coding! Remember, discovering solutions on your own is the best way to learn!

# General Hints

## Changing color of ball
- Note the inheritance of the Ball class - what kind of JavaFX object is it?
- Look into the [javafx.scene.shape.Shape documentation](https://openjfx.io/javadoc/25/javafx.graphics/javafx/scene/shape/Shape.html)] to find the method that can be used to fill the ball with solid color.

## Increase the ball’s speed every time it bounces off the pads
- Identify the variables that represents the ball's horizontal and vertical speed.
- Figure out when the right moment is to increase the speed (in this case, every time the ball bounces of the pad).
- Increase the values of the speed variables at the right time.

## Introduce gravity
- Identify the variable that represent the ball's vertical speed.
- Figure out a way to modify this variable every frame to make the fall down over time.

## Introduce wind
- Now that you know how to increase ball's speed and introduce gravity, think about how you can modify the variable for vertical and horizontal speed to mimic interaction of the ball with (constant or variable) wind.

## Introduce sound
- Look into [javafx.scene.media.AudioClip](https://openjfx.io/javadoc/25/javafx.media/javafx/scene/media/AudioClip.html)


# Specific Hints

## Changing color of ball
- Use the [setFill method](https://openjfx.io/javadoc/25/javafx.graphics/javafx/scene/shape/Shape.html#setFill(javafx.scene.paint.Paint)) to fill the ball (which is a Circle) object with solid color.

## Increase the ball’s speed every time it bounces off the pads
- The variables `dx` and `dy` represent horizontal and vertical speed.
- Note that these variables contains the exact delta that is added to the balls position for each frame. A positive `dx` value will add to the X coordinate (move the ball to the right), and a negative `dx` value will decrease the X coordinate. The same is true for the `dy` variable with respect to the Y position of the ball.
- Implement an `increaseSpeed` method that increases `dx` and `dy` by a given value.
- Call the method inside the conditional code that checks for collision. Note that the "increment" must be in the right direction.

## Introduce gravity
- The variable `dy` represents the ball's vertical speed.
- Find the method that moves the ball and add a small constant value to `dy`.

## Introduce wind
- Add a small value to `dx` and/or `dy` in the method that moves the ball.

## Introduce sound
- Find or record a suitable audio file.
- Import `javafx.scene.media.AudioClip` into Pong.java.
- Initialize an AudioClip (provide the correct path your sound file).
- Play the AudioClip using the [play method](https://openjfx.io/javadoc/25/javafx.media/javafx/scene/media/AudioClip.html#play()).

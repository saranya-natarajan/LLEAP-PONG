# Introduction
This document is designed to guide you in enhancing your Pong game through a series of hints. Rather than providing ready-made code solutions, we encourage you to engage deeply with the material and strengthen your problem-solving skills by working through these challenges.

The document is divided into two sections: general hints and specific hints. We recommend that you begin with the general hints to develop your own ideas and strategies, then consult the specific hints if you need more guidance as you implement your improvements.

**Note that the hints here illustrate one way to implement the suggested improvements but there are many other ways to do the same.**

### Happy coding! Remember discovering solutions on your own is the best way to learn!

# General Hints

## Changing color of ball
- Look into [javafx.scene.shape documentation](https://openjfx.io/javadoc/25/javafx.graphics/javafx/scene/shape/package-summary.html) to find the method that can be used to fill the ball with solid color.

## Increase the ball’s speed every time it bounces off the pads
- Identify the variable that represents the ball's horizontal velocity.
- Introduce and apply a multiplier to this speed variable.
- Think about the right moment the speed should increase (in this case everytime the ball bounces of the pad).

## Introduce gravity
- Identify the variable that represent the ball's vertical speed.
- Incrementally increase this speed variable for every frame to make it fall down over time.

## Introduce wind
- Now that you know how to increase ball's speed and introduce gravity, think about how you can modify the variable for vertical and horizontal speed to mimic interaction of ball with (constant or variable) wind

## Introduce sound
- Look into [javafx.scene.media.AudioClip](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/media/AudioClip.html)


# Specific Hints

## Changing color of ball
Use the setFill method ([javafx.scene.shape setFill method details](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/shape/Shape.html#setFill-javafx.scene.paint.Paint-)]) to fill the ball object with solid color.

## Increase the ball’s speed every time it bounces off the pads
- The variables `dx` represent horizontal speed.
- Implement `increaseSpeed` method that increases `dx` by a factor of your choice.
- Call the method inside the condition that checks from collision after handling collision.

## Introduce gravity
- The variable `dy` represents the ball's vertical speed.
- Find the method that moves the ball and add a constant to `dy`

## Introduce wind
- Add a small value `dx` and/or `dy` to the method that moves the ball.

## Introduce sound
- Download or use audio file on internet
- Import `javafx.scene.media.AudioClip` to Pong.java
- Initialize AudioClip (provide correct path your sound file)
- Play the AudioClip using [play method](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/media/AudioClip.html#play--)

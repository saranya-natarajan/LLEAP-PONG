# LLEAP Pong Assignment

Explore a Pong game using Java and JavaFX!
* Download the JDK: https://jdk.java.net/25/
* Download JavaFX: https://jdk.java.net/javafx25/
* Java SE documentation: https://docs.oracle.com/en/java/javase/25/docs
* JavaFX documentation: https://wiki.openjdk.org/spaces/OpenJFX/overview
* Homepage: https://cr.openjdk.org/~jwilhelm/PeppStockholm/

## Installing

Extract the JDK and JavaFX package inside this folder.

Your folder structure should look like this on macOS/Linux:
```
-- LLEAP-PONG/
   |-- Ball.java
   |-- Direction.java
   |-- javafx-sdk-25.0.2/
   |-- jdk-25.0.2.jdk/
   ...
```

And like this on Windows:
```
-- LLEAP-PONG\
   |-- Ball.java
   |-- Direction.java
   |-- javafx-sdk-25.0.2\
   |-- jdk-25.0.2\
   ...
```

## Running

Run the command corresponding to your OS in the terminal:

macOS:
```
jdk-25.0.2.jdk/Contents/Home/bin/java --module-path javafx-sdk-25.0.2/lib --add-modules javafx.controls --enable-native-access=javafx.graphics Pong.java
```

Linux:
```
jdk-25.0.2.jdk/bin/java --module-path javafx-sdk-25.0.2/lib --add-modules javafx.controls --enable-native-access=javafx.graphics Pong.java
```

Windows:
```
jdk-25.0.2\bin\java --module-path javafx-sdk-25.0.2\lib --add-modules javafx.controls --enable-native-access=javafx.graphics Pong.java
```

## Modifying

You can edit the code in whatever text editor you prefer.
Visual Studio Code is a popular option.
Once you're done editing, just re-run the program as above.

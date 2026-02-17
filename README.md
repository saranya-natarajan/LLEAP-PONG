# LLEAP Pong Assignment

Explore a Pong game using Java and JavaFX!
* Download the JDK: https://jdk.java.net/25/
* Download JavaFX: https://jdk.java.net/javafx25/
* Java SE documentation: https://docs.oracle.com/en/java/javase/25/docs
* JavaFX documentation: https://wiki.openjdk.org/spaces/OpenJFX/overview
* Homepage: https://cr.openjdk.org/~jwilhelm/PeppStockholm/

## Running

macOS:
```
jdk-25.0.2.jdk/Contents/Home/bin/javac --module-path javafx-sdk-25.0.2/lib --add-modules javafx.controls Pong/Pong.java
jdk-25.0.2.jdk/Contents/Home/bin/java --module-path javafx-sdk-25.0.2/lib --add-modules javafx.controls --enable-native-access=javafx.graphics Pong.Pong
```

Linux:
```
jdk-25.0.2.jdk/bin/javac --module-path javafx-sdk-25.0.2/lib --add-modules javafx.controls Pong/Pong.java
jdk-25.0.2.jdk/bin/java --module-path javafx-sdk-25.0.2/lib --add-modules javafx.controls --enable-native-access=javafx.graphics Pong.Pong
```

Windows:
```
del Pong\*.class
jdk-25.0.2\bin\javac --module-path javafx-sdk-25.0.2\lib --add-modules javafx.controls Pong\Pong.java
jdk-25.0.2\bin\java --module-path javafx-sdk-25.0.2\lib --add-modules javafx.controls --enable-native-access=javafx.graphics Pong.Pong
```

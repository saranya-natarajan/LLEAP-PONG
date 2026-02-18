>>>>> PEPP PONG ASSIGNMENT <<<<<

Explore a Pong game using Java and JavaFX!
* Download the JDK: https://jdk.java.net/25/
* Download JavaFX: https://jdk.java.net/javafx25/
* Java SE documentation: https://docs.oracle.com/en/java/javase/25/docs
* JavaFX documentation: https://wiki.openjdk.org/spaces/OpenJFX/overview
* Homepage: https://cr.openjdk.org/~jwilhelm/PeppStockholm/


>>>>> INSTALLING <<<<<

This guide assumes that everything is in your Downloads folder!

[!] IMPORTANT: If you choose another folder than Downloads,
    you must adjust your steps later.

Extract the JDK and JavaFX folders next to the Pong/ folder.
Assuming that all three are placed in your downloads folder,
your structure should look like this:


macOS, Linux:
---------------

Downloads/
	|-- Pong/
    	|-- Ball.java
    	|-- Direction.java
    	|-- ...
   	|-- javafx-sdk-25.0.2/
   	|-- jdk-25.0.2.jdk/

Windows:
---------------

Downloads\
	|-- Pong\
    	|-- Ball.java
		|-- Direction.java
		|-- ...
   	|-- javafx-sdk-25.0.2\
   	|-- jdk-25.0.2\

[!] IMPORTANT: Make sure to keep the folder name as "Pong", case sensitive.

>>>>> RUNNING THE CODE <<<<<

Run the commands corresponding to your OS in a terminal to compile and launch
the code. You should run these from the context of the downloads folder.

The three commands do the following:
1. Delete any old compiled artifacts
2. Compile the Java code
3. Run the compiled code

macOS:
----------

rm Pong/*.class
jdk-25.0.2.jdk/Contents/Home/bin/javac --module-path javafx-sdk-25.0.2/lib --add-modules javafx.controls Pong/Pong.java
jdk-25.0.2.jdk/Contents/Home/bin/java --module-path javafx-sdk-25.0.2/lib --add-modules javafx.controls --enable-native-access=javafx.graphics Pong.Pong

Linux:
----------

rm Pong/*.class
jdk-25.0.2.jdk/bin/javac --module-path javafx-sdk-25.0.2/lib --add-modules javafx.controls Pong/Pong.java
jdk-25.0.2.jdk/bin/java --module-path javafx-sdk-25.0.2/lib --add-modules javafx.controls --enable-native-access=javafx.graphics Pong.Pong

Windows:
----------

del Pong\*.class
jdk-25.0.2\bin\javac --module-path javafx-sdk-25.0.2\lib --add-modules javafx.controls Pong\Pong.java
jdk-25.0.2\bin\java --module-path javafx-sdk-25.0.2\lib --add-modules javafx.controls --enable-native-access=javafx.graphics Pong.Pong

>>>>> MODIFYING THE CODE <<<<<

You can edit the code in whatever text editor you prefer. Please note that
Word is not a text editor - it will not save your text as a clean text file.
Visual Studio Code is a popular option. Once you're done editing,
just re-run the program as above.

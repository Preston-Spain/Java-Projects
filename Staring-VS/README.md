# To run Java in VS Code, you need:
* You will likely need a hotspot!!!
* press (Ctrl + Shift + X) and search for:
* Java
* Java Development Kit (JDK)
* If this still doesn't work then google 'how to install java' or sm IDK

# Now that Java is install, you need to make some changes:
* throwing your code into a file named '___.java'
* Name the file whatever you want, but make sure it ends in .java and is relivent
* EX. HelloWorld.java prints 'Hello World'
* Now I know, because you use online compilers, most of you have something like:
```java
// Bla bla bla imports

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
```
* But in VS Code, and running any java file legitimately, you need to change some small things like this:
* EX. HelloWorld.java 
```java
// Bla bla bla imports

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
```
* If you recieve an error like this:
```shell
ERROR!
Error: Could not find or load main class Main
Caused by: java.lang.ClassNotFoundException: Main
```
* Then you need to change the class name to match the file name, fix it.

# Now to run the file:
* save the file (ctrl + s)
* if the file manager shows up, ask Cooper or Logan for help, who ever is there
* (To Cooper and Logan) You may not need the stuff here, just in case
* (To Cooper and Logan) Find a spot to make a folder, call it "Code" and put another folder inside of it, "middle-school" or sm
* (To Cooper and Logan) inside the "middle-school" folder, make a folder called "Students name" *Not literally, like the kids name*
* (To Cooper and Logan) open the "Students name" folder, and click slect folder.
* Now if you folowed every step, you can press (F5), or up in the top left corner, there is a button  that says "Run", then click "Start Debugging"
* If this doesn't work, copy the error and search it in google, I can not be asked to write every posibility, or ask Cooper or Logan for help. 
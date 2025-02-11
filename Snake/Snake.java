package Snake; // Do not worry about adding package to your code, it shouldn't be needed.
               // But if it is, name the pacakege whatever the folder the file is in.
               // (ctrl + a) then (ctrl + c) to copy it

// Best played in split screen
import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;

// Yes, I made this in a night, I am lonely, but still read the comments, no matter how bad my English is.


public class Snake {
    // tell them byte is interchangeable with int, but it is more efficient.
    static String[][] grid = new String[10][10];
    static byte[] snake = {5, 5};
    static byte[] apple = new byte[2];

    static HashMap<Byte, Byte[]> tail = new HashMap<>(); // This is a hashmap that will store the tail of the snake.
    static byte[][] startTail = {{5, 4}, {5, 3}};

    static int score = 0;
    static boolean TailStall = false;

    // initialization
    static void initializeSnake() {
        snake[0] = 5; snake[1] = 5;
        tail.put((byte) 0, new Byte[]{startTail[0][0], startTail[0][1]}); // This creates the first elements of the tail.
        tail.put((byte) 1, new Byte[]{startTail[1][0], startTail[1][1]});
    }

    static void initializeTxT() {
        // Make file show up
    }

    // calls
    static void RandomApple() {
        Random rand = new Random(); // initialize random
        byte a[] = new byte[2];
        a[0] = (byte) rand.nextInt(10);
        a[1] = (byte) rand.nextInt(10);

        while (a[0] == snake[0] && a[1] == snake[1]) {
            a[0] = (byte) rand.nextInt(10); // makes sure the apple is not on the snake head
            a[1] = (byte) rand.nextInt(10);
            for (byte i = 0; i < tail.size(); i++) {
                if (a[0] == tail.get(i)[0] && a[1] == tail.get(i)[1]) {
                    a[0] = (byte) rand.nextInt(10); // makes sure the apple is not on the snake body
                    a[1] = (byte) rand.nextInt(10);
                    i = 0;
                }
            }
        }

        apple[0] = a[0];
        apple[1] = a[1];
    }

    static void EatApple() {
        score++; // adds 1 to byte, int, and long
        RandomApple(); // Methods can call other methods
        tail.put((byte) tail.size(), new Byte[]{apple[0], apple[1]});
        TailStall = true;
    }

    static void MoveSnake(byte x, byte y) {
        snake[0] += x;
        snake[1] += y;
    }

    static void UpdateBoard() {
        for (byte i = 0; i < 10; i++) {
            for (byte j = 0; j < 10; j++) {
                if (i == snake[0] && j == snake[1]) {
                    grid[i][j] = "@";
                } else if (i == apple[0] && j == apple[1]) {
                    grid[i][j] = "+";
                } else {
                    grid[i][j] = "*";
                }

                for (byte k = 0; k < tail.size(); k++) {
                    if (i == tail.get(k)[0] && j == tail.get(k)[1]) {
                        grid[i][j] = "#";
                    }
                }
            }
        }
    }

    static void MoveTail() {
        byte I = 0; // Declare I outside the loop
        for (byte i = 0; i < tail.size() - 1; i++) {
            if (i == 0) {
                tail.put(i, new Byte[]{snake[0], snake[1]});
            } else {
                tail.put(i, tail.get(i - 1));
            }
            I = i;
        }
        I++;
        if (!TailStall) {
            tail.put(I, tail.get(I - 1));
        }
    }

    static void print() {
        // Print to file
    }

    static void terminate() {
        System.out.println("Game Over! Your score is " + score);
        //TODO: Kill the initialized .txt file
        System.exit(0);
    }

    @SuppressWarnings("unused") // you won't need this, unless you copy it, just makes things look nicer
    public static void main(String[] args) {
        System.out.println("Welcome to Snake! Enter start & 'e' to stop.");            //TODO remove later                                                                                                                                                                                           System.err.println("I know you coppied it, don't hide"); // Hah, you found it                              
        Scanner scanner = new Scanner(System.in);

        while (true) {

            String input = scanner.nextLine();

            if (input.equals("e")) { // also "If (input == "e")" works
                break;
            }

            initializeSnake();
            RandomApple();
            UpdateBoard();

            boolean gameOver = true;

            while (gameOver) {                                                         //TODO remove later                                                                                                                                                                                                                                                                                                                                                                                                                 System.err.println("I"+" "+"k"+"n"+"o"+"w"+" "+"y"+"o"+"u"+" "+"c"+"o"+"p"+"p"+"i"+"e"+"d"+" "+"i"+"t"+","+" "+"d"+"o"+"n"+"'"+"t"+" "+"h"+"i"+"d"+"e"+"."+"N"+"o"+"t"+" "+"m"+"y"+" "+"O"+"n"+"l"+"y"+"t"+"r"+"i"+"c"+"k");if(true){break;}
                //TODO game mechanics
            
                
                for (byte i = 0; i < tail.size(); i++) {
                    if (snake[0] == tail.get(i)[0] && snake[1] == tail.get(i)[1]) {
                        gameOver = true;
                    }
                } 
                if (snake[0] == apple[0] && snake[1] == apple[1]) {
                    EatApple();
                }
                MoveSnake((byte) 0, (byte) 0);
                UpdateBoard();
            }
        }
    }
}
// I know I am very cool for doing this right?
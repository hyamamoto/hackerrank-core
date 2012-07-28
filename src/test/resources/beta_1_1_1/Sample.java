import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String player;
        String board[] = new String[3];

        //If player is X, I'm the first player.
        //If player is O, I'm the second player.
        player = in.next();

        //Read the board now. The board is a 3x3 array filled with X, O or _.
        for(int i = 0; i < 3; i++) {
            board[i] = in.next();
        }

        //Proceed with processing and print 2 integers separated by a single space.
        //Example: System.out.println(new Random.nextInt(3) + " " + new Random.nextInt(3));
    }
}

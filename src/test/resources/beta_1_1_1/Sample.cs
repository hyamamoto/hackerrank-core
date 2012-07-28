using System;
using System.Collections.Generic;
using System.IO;
class Solution {
    static void Main(String[] args) {
        String player;

        //If player is X, I'm the first player.
        //If player is O, I'm the second player.
        player = Console.ReadLine();

        //Read the board now. The board is a 3x3 array filled with X, O or _.
        String[] board  = new String[3];
        for(int i=0; i < 3; i++) {
            board[i] = Console.ReadLine(); 
        }

        //Proceed with processing and print 2 integers separated by a single space.
        //Example: System.out.println(new Random.next(3) + " " + new Random.next(3));
    }
}


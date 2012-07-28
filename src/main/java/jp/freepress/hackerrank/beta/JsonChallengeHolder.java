package jp.freepress.hackerrank.beta;

// {"model":{"id":1,"slug":"tic-tac-toe","name":"Tic tac toe","body":"Tic-tac-toe, also called wick wack woe (in some Asian countries) and noughts and crosses (in the British Commonwealth countries), is a pencil-and-paper game for two players, X and O, who take turns marking the spaces in a 3\u00d73 grid.\r\n\r\nThe X player usually goes first. The player who succeeds in placing three respective marks in a horizontal, vertical, or diagonal row wins the game.\r\n\r\nThe following example game is won by the first player, X:\r\n\r\n![picture alt](https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/Tic-tac-toe-game-1.svg/500px-Tic-tac-toe-game-1.svg.png \"Title is optional\")\r\n\r\nIn this game, you will be given the board state as input. You are expected to print the next move of the game.\r\n\r\n__Input format:__  \r\nThe first line in the input will be a single character. X or O.  \r\nIf the first line is X, then you are the first player and all X in the board belongs to you.  \r\nIf the first line is O, then you are the second player and all O in the board belongs to you.  \r\nThe following 3 lines is the current board state.  \r\nEach line will have 3 characters.  \r\nThe characters can be either (X, O, or _)  \r\nX means the box position is occupied by the first player. O means the box position is occupied by the second player. _ means the box is not yet occupied.\r\n\r\n__Output format:__  \r\n2 integers( row column ) separated by a single space, which specifies the position of the board you want to make your move. ( 0 indexed )\r\n\r\n__Input 00:__\r\n\r\n    X  \r\n    ___  \r\n    ___  \r\n    _XO  \r\n\r\n__Output 00:__  \r\n\r\n    0 0 \r\n\r\n__Explanation 00:__  \r\nThe board results in the following state after the above move   \r\n\r\n    X__  \r\n    ___  \r\n    _XO  ","status":null,"_data":{"game_name":"tictactoe","c_template":"#include <stdio.h>\n#include <string.h>\n#include <math.h>\n\nint main() {\n\n    int i;\n    char player;\n    char board[3][3];\n\n    //If player is X, I'm the first player.\n    //If player is O, I'm the second player.\n    scanf(\"%c\", &player);\n\n    //Read the board now. The board is a 3x3 array filled with X, O or _.\n    for(i=0; i<3; i++) {\n        scanf(\"%s[^\\n]%*c\", board[i]);\n    }\n\n    //Proceed with processing and print 2 integers separated by a single space.\n    //Example: printf(\"%d %d\", rand()%3, rand()%3);\n\n    return 0;\n}\n","cpp_template":"#include <map>\n#include <set>\n#include <list>\n#include <cmath>\n#include <ctime>\n#include <deque>\n#include <queue>\n#include <stack>\n#include <bitset>\n#include <cstdio>\n#include <vector>\n#include <cstdlib>\n#include <numeric>\n#include <sstream>\n#include <iostream>\n#include <algorithm>\nusing namespace std;\n\nint main() {\n\n    char player;\n    vector <string> board(3);\n\n    //If player is X, I'm the first player.\n    //If player is O, I'm the second player.\n    cin >> player;\n\n    //Read the board now. The board is a 3x3 array filled with X, O or _.\n    for(int i=0; i<3; i++) {\n        cin >> board[i];\n    }\n\n    //Proceed with processing and print 2 integers separated by a single space.\n    //Example: printf(\"%d %d\", rand()%3, rand()%3);\n\n    return 0;\n}\n","java_template":"import java.io.*;\nimport java.util.*;\nimport java.text.*;\nimport java.math.*;\nimport java.util.regex.*;\n\npublic class Solution {\n    public static void main(String[] args) {\n        Scanner in = new Scanner(System.in);\n        String player;\n        String board[] = new String[3];\n\n        //If player is X, I'm the first player.\n        //If player is O, I'm the second player.\n        player = in.next();\n\n        //Read the board now. The board is a 3x3 array filled with X, O or _.\n        for(int i = 0; i < 3; i++) {\n            board[i] = in.next();\n        }\n\n        //Proceed with processing and print 2 integers separated by a single space.\n        //Example: System.out.println(new Random.nextInt(3) + \" \" + new Random.nextInt(3));\n    }\n}\n","csharp_template":"using System;\nusing System.Collections.Generic;\nusing System.IO;\nclass Solution {\n    static void Main(String[] args) {\n        String player;\n\n        //If player is X, I'm the first player.\n        //If player is O, I'm the second player.\n        player = Console.ReadLine();\n\n        //Read the board now. The board is a 3x3 array filled with X, O or _.\n        String[] board  = new String[3];\n        for(int i=0; i < 3; i++) {\n            board[i] = Console.ReadLine(); \n        }\n\n        //Proceed with processing and print 2 integers separated by a single space.\n        //Example: System.out.println(new Random.next(3) + \" \" + new Random.next(3));\n    }\n}\n","php_template":"<?php\n\n$fp = fopen(\"php://stdin\", \"r\");\n\n//If player is X, I'm the first player.\n//If player is O, I'm the second player.\nfscanf($fp, \"%d\", $player);\n\n//Read the board now. The board is a 3x3 array filled with X, O or _.\n$board = array();\nfor ($i=0; $i<3; $i++) { \n    fscanf($fp, \"%s\", $board[$i]);\n}\n\n//Proceed with processing and print 2 integers separated by a single space.\n//Example: echo rand()%3, ' ', rand()%3;\n\n\n?>\n","ruby_template":"#!/bin/ruby\n\n#If player is X, I'm the first player.\n#If player is O, I'm the second player.\nplayer = gets;\n\n#Read the board now. The board is a 3x3 array filled with X, O or _.\nboard = Array.new(3);\n\nfor i in Range.new(0, 2).to_a\n    board[i] = gets;\nend\n\n#Proceed with processing and print 2 integers separated by a single space.\n#Example: print Random.rand(3), ' ',  Random.rand(3)\n","python_template":"#!/bin/python\n\n#If player is X, I'm the first player.\n#If player is O, I'm the second player.\n\nplayer = raw_input()\n\n#Read the board now. The board is a 3x3 array filled with X, O or _.\nboard = []\nfor i in xrange(0, 3):\n    board.append(raw_input())\n\n\n#Proceed with processing and print 2 integers separated by a single space.\n#Example: print random.randint(0, 2), random.randint(0, 2)\n","perl_template":"#!/usr/bin/perl\n\n#If player is X, I'm the first player.\n#If player is O, I'm the second player.\n$player = <>;\n\n#Read the board now. The board is a 3x3 array filled with X, O or _.\nfor ($i=0;$i<3;$i++) {\n    $board[$i] = <>;\n}\n\n#Proceed with processing and print 2 integers separated by a single space.\n#Example: print int(rand(3)), ' ', int(rand(3));\n","haskell_template":"module Main where\n\ngetList n = if n==0 then return [] else do i <- getLine; is <- getList(n-1); return (i:is)\n\nmain = do\n\n    -- If player is X, I'm the first player.\n    -- If player is O, I'm the second player.\n    player <- getLine\n\n    -- Read the board now. The board is a 3x3 array filled with X, O or _.\n    board <- getList 3\n\n    -- Proceed with processing and print 2 integers separated by a single space.\n    -- Example: putStrLn RandomGen.genRange(0, 3) + \" \" + RandomGen.genRange(0, 3)\n\n    return 0\n\n\n","clojure_template":"(ns solution (:gen-class))\n\n(defn -main []\n (\n  let [ player (read-line), board (doall (map #(take 3 (rest read-line)))) ] \n )\n)\n","scala_template":"object Solution {\n    def main(args: Array[String]) = { \n        val player = Console.readLine\n        val board = new Array[String](3)\n        for (i <- 0 until 3) {\n            board.update(i, Console.readLine)\n        }\n    }\n}\n"},"created_at":"2012-07-19T17:13:20Z","updated_at":"2012-07-19T17:48:11Z","category_id":1,"contest_id":1,"dynamic":true,"kind":"game","level":1,"preview":"Tic-tac-toe, also called wick wack woe (in some Asian countries) and noughts and crosses (in the British Commonwealth countries), is a pencil-and-paper game for two players, X and O, who take turns marking the spaces in a 3\u00d73 grid.\r\n\r\nWrite the best bot for Tic Tac Toe.","body_html":"<!-- === begin markdown block =====================================================\n\n      generated by markdown 1.0.0 on Ruby 1.9.3 (2012-04-20) [i686-linux]\n                on 2012-07-24 22:37:37 +0000 with Markdown engine kramdown (0.13.7)\n                  using options { !to be done! }\n  -->\n<p>Tic-tac-toe, also called wick wack woe (in some Asian countries) and noughts and crosses (in the British Commonwealth countries), is a pencil-and-paper game for two players, X and O, who take turns marking the spaces in a 3\u00d73 grid.</p>\n\n<p>The X player usually goes first. The player who succeeds in placing three respective marks in a horizontal, vertical, or diagonal row wins the game.</p>\n\n<p>The following example game is won by the first player, X:</p>\n\n<p><img src=\"https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/Tic-tac-toe-game-1.svg/500px-Tic-tac-toe-game-1.svg.png\" alt=\"picture alt\" title=\"Title is optional\" /></p>\n\n<p>In this game, you will be given the board state as input. You are expected to print the next move of the game.</p>\n\n<p><strong>Input format:</strong><br />\nThe first line in the input will be a single character. X or O.<br />\nIf the first line is X, then you are the first player and all X in the board belongs to you.<br />\nIf the first line is O, then you are the second player and all O in the board belongs to you.<br />\nThe following 3 lines is the current board state.<br />\nEach line will have 3 characters.<br />\nThe characters can be either (X, O, or _)<br />\nX means the box position is occupied by the first player. O means the box position is occupied by the second player. _ means the box is not yet occupied.</p>\n\n<p><strong>Output format:</strong><br />\n2 integers( row column ) separated by a single space, which specifies the position of the board you want to make your move. ( 0 indexed )</p>\n\n<p><strong>Input 00:</strong></p>\n\n<pre><code>X  \n___  \n___  \n_XO  \n</code></pre>\n\n<p><strong>Output 00:</strong>  </p>\n\n<pre><code>0 0 \n</code></pre>\n\n<p><strong>Explanation 00:</strong><br />\nThe board results in the following state after the above move   </p>\n\n<pre><code>X__  \n___  \n_XO  \n</code></pre>\n<!-- === end markdown block ===================================================== -->\n","neo":{"graph_stats":[]}}}

/**
 * A class for a wrapper of a challenge JSON text.
 */
public final class JsonChallengeHolder {

  private JsonChallenge model;

  public JsonChallengeHolder() {
    super();
  }

  public JsonChallenge getModel() {
    return model;
  }

  public void setModel(JsonChallenge model) {
    this.model = model;
  }

}

<?php

$fp = fopen("php://stdin", "r");

//If player is X, I'm the first player.
//If player is O, I'm the second player.
fscanf($fp, "%d", $player);

//Read the board now. The board is a 3x3 array filled with X, O or _.
$board = array();
for ($i=0; $i<3; $i++) { 
    fscanf($fp, "%s", $board[$i]);
}

//Proceed with processing and print 2 integers separated by a single space.
//Example: echo rand()%3, ' ', rand()%3;


?>


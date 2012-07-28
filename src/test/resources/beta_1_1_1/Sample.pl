#!/usr/bin/perl

#If player is X, I'm the first player.
#If player is O, I'm the second player.
$player = <>;

#Read the board now. The board is a 3x3 array filled with X, O or _.
for ($i=0;$i<3;$i++) {
    $board[$i] = <>;
}

#Proceed with processing and print 2 integers separated by a single space.
#Example: print int(rand(3)), ' ', int(rand(3));


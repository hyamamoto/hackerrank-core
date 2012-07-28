#!/bin/ruby

#If player is X, I'm the first player.
#If player is O, I'm the second player.
player = gets;

#Read the board now. The board is a 3x3 array filled with X, O or _.
board = Array.new(3);

for i in Range.new(0, 2).to_a
    board[i] = gets;
end

#Proceed with processing and print 2 integers separated by a single space.
#Example: print Random.rand(3), ' ',  Random.rand(3)


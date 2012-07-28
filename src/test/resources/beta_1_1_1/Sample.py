#!/bin/python

#If player is X, I'm the first player.
#If player is O, I'm the second player.

player = raw_input()

#Read the board now. The board is a 3x3 array filled with X, O or _.
board = []
for i in xrange(0, 3):
    board.append(raw_input())


#Proceed with processing and print 2 integers separated by a single space.
#Example: print random.randint(0, 2), random.randint(0, 2)


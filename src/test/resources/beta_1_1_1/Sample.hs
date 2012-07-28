module Main where

getList n = if n==0 then return [] else do i <- getLine; is <- getList(n-1); return (i:is)

main = do

    -- If player is X, I'm the first player.
    -- If player is O, I'm the second player.
    player <- getLine

    -- Read the board now. The board is a 3x3 array filled with X, O or _.
    board <- getList 3

    -- Proceed with processing and print 2 integers separated by a single space.
    -- Example: putStrLn RandomGen.genRange(0, 3) + " " + RandomGen.genRange(0, 3)

    return 0




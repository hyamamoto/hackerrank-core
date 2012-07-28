object Solution {
    def main(args: Array[String]) = { 
        val player = Console.readLine
        val board = new Array[String](3)
        for (i <- 0 until 3) {
            board.update(i, Console.readLine)
        }
    }
}

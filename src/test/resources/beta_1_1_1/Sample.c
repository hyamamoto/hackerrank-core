#include <stdio.h>
#include <string.h>
#include <math.h>

int main() {

    int i;
    char player;
    char board[3][3];

    //If player is X, I'm the first player.
    //If player is O, I'm the second player.
    scanf("%c", &player);

    //Read the board now. The board is a 3x3 array filled with X, O or _.
    for(i=0; i<3; i++) {
        scanf("%s[^\n]%*c", board[i]);
    }

    //Proceed with processing and print 2 integers separated by a single space.
    //Example: printf("%d %d", rand()%3, rand()%3);

    return 0;
}

#include <map>
#include <set>
#include <list>
#include <cmath>
#include <ctime>
#include <deque>
#include <queue>
#include <stack>
#include <bitset>
#include <cstdio>
#include <vector>
#include <cstdlib>
#include <numeric>
#include <sstream>
#include <iostream>
#include <algorithm>
using namespace std;

int main() {

    char player;
    vector <string> board(3);

    //If player is X, I'm the first player.
    //If player is O, I'm the second player.
    cin >> player;

    //Read the board now. The board is a 3x3 array filled with X, O or _.
    for(int i=0; i<3; i++) {
        cin >> board[i];
    }

    //Proceed with processing and print 2 integers separated by a single space.
    //Example: printf("%d %d", rand()%3, rand()%3);

    return 0;
}

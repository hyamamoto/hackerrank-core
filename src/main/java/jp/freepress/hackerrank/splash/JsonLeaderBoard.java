package jp.freepress.hackerrank.splash;

import java.util.Arrays;

// https://www.hackerrank.com/splash/leaderboard.json
// {"board":[{"score":50000.0,"rank":1,"candies_game_count":2129,"user":"yy"},{"score":42108.0,"rank":2,"candies_game_count":2129,"user":"bmv437"},{"score":2330.0,"rank":3,"candies_game_count":2129,"user":"obliojoe"},{"score":603.0,"rank":4,"candies_game_count":0,"user":"AntonHola"},{"score":505.0,"rank":5,"candies_game_count":0,"user":"ajite"},{"score":496.0,"rank":6,"candies_game_count":210,"user":"jeffsergeant2"},{"score":357.0,"rank":7,"candies_game_count":12,"user":"x"},{"score":330.0,"rank":8,"candies_game_count":15,"user":"dstrand"},{"score":312.0,"rank":9,"candies_game_count":15,"user":"nimmi9"},{"score":273.0,"rank":10,"candies_game_count":0,"user":"arya5691"},{"score":250.0,"rank":11,"candies_game_count":0,"user":"simil"},{"score":194.0,"rank":12,"candies_game_count":27,"user":"Scardraker"},{"score":165.0,"rank":13,"candies_game_count":4,"user":"traceformula"},{"score":148.0,"rank":14,"candies_game_count":11,"user":"quantum"},{"score":136.0,"rank":15,"candies_game_count":1367,"user":"mgamache"}]}
// {"board":[{"score":69605.0,"rank":1,"candies_game_count":0,"user":"ImKBrizzle"},{"score":55500.0,"rank":2,"candies_game_count":2129,"user":"rINDmUNNA"},{"score":55500.0,"rank":2,"candies_game_count":2129,"user":"lumansfastbot"},{"score":55500.0,"rank":2,"candies_game_count":2180,"user":"argggh"},{"score":55500.0,"rank":2,"candies_game_count":1337,"user":"jcubot"},{"score":55500.0,"rank":2,"candies_game_count":1337,"user":"mediaupstream"},{"score":55500.0,"rank":2,"candies_game_count":2129,"user":"learc83"},{"score":55500.0,"rank":2,"candies_game_count":2129,"user":"obliojoe23"},{"score":55500.0,"rank":2,"candies_game_count":2129,"user":"ccube"},{"score":55500.0,"rank":2,"candies_game_count":1806,"user":"higon2"},{"score":55500.0,"rank":2,"candies_game_count":2129,"user":"mumpen2"},{"score":55498.0,"rank":12,"candies_game_count":2129,"user":"lumantest5"},{"score":55496.0,"rank":13,"candies_game_count":2129,"user":"mumpen"},{"score":55493.0,"rank":14,"candies_game_count":2129,"user":"nosse"},{"score":55493.0,"rank":14,"candies_game_count":1350,"user":"higon"}]}

/**
 * A class for a leaderboard JSON text. You get this object as the result of
 * <code>leaderboard()</code>
 */
public final class JsonLeaderBoard {

  private JsonUserStat[] board;

  public JsonLeaderBoard() {
    super();
  }

  public JsonUserStat[] getBoard() {
    return board;
  }

  public void setBoard(JsonUserStat[] board) {
    this.board = board;
  }

  @Override
  public String toString() {
    return "LeaderBoard [board=" + Arrays.toString(board) + "]";
  }

}

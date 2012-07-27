package jp.freepress.hackerrank.splash;

import org.apache.commons.lang3.StringUtils;

// https://www.hackerrank.com/splash/userstats.json
// {"rank":1,"score":55500,"user":"someone","candies_game_count":2129}
// {"rank":106,"score":0.0,"user":"higon","candies_game_count":0}

/**
 * A class for a user status JSON Object. You get this object as the result of
 * <code>userstats()</code> or <code>leaderboard()</code>
 */
public final class JsonUserStat {

  private int rank;
  private double score;
  private String user;
  private int candies_game_count;

  public JsonUserStat() {
    super();
  }

  public int getRank() {
    return rank;
  }

  public void setRank(int rank) {
    this.rank = rank;
  }

  public double getScore() {
    return score;
  }

  public void setScore(double score) {
    this.score = score;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public int getCandies_game_count() {
    return candies_game_count;
  }

  public void setCandies_game_count(int candies_game_count) {
    this.candies_game_count = candies_game_count;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("UserStat [rank=");
    builder.append(rank);
    builder.append(", score=");
    builder.append(score);
    builder.append(", user=");
    builder.append(user);
    builder.append(", candies_game_count=");
    builder.append(candies_game_count);
    builder.append("]");
    return builder.toString();
  }

  public static class Util {
    public static String toSimpleLineString(JsonUserStat stat) {
      StringBuilder builder = new StringBuilder();
      builder.append("[");
      builder.append(StringUtils.leftPad(Integer.toString(stat.rank), 4));
      builder.append("] ");
      builder.append(StringUtils.rightPad(stat.user, 18));
      builder.append(",");
      builder.append(StringUtils.leftPad(Double.toString(stat.score), 8));
      builder.append(" pt");
      builder.append(stat.score <= 1d ? " ," : "s,");
      builder.append(StringUtils.leftPad(Double.toString(stat.candies_game_count), 7));
      builder.append(" cand");
      builder.append(stat.candies_game_count <= 1d ? "y" : "ies");
      return builder.toString();
    }
  }
}

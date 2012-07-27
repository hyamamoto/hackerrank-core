package jp.freepress.hackerrank.beta;

import org.apache.commons.lang3.StringUtils;

public final class JsonLeaderBoard {
  
  private int rank;

  private double score;
 
  private String hacker;
 
  private String country;
 
  private String language;
  
  public JsonLeaderBoard() {
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

  public String getHacker() {
    return hacker;
  }

  public void setHacker(String hacker) {
    this.hacker = hacker;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public static class Util {
    public static String toSimpleLineString(JsonLeaderBoard stat) {
      StringBuilder builder = new StringBuilder();
      builder.append("[");
      builder.append(StringUtils.leftPad(Integer.toString(stat.rank), 4));
      builder.append("] ");
      builder.append(StringUtils.rightPad(stat.hacker, 18));
      builder.append(",");
      builder.append(StringUtils.leftPad(Double.toString(stat.score), 8));
      builder.append(" pt");
      builder.append(stat.score <= 1d ? " , " : "s, ");
      builder.append(StringUtils.rightPad(stat.language, 10));
      builder.append(",");
      builder.append(StringUtils.rightPad(stat.country, 16));
      return builder.toString();
    }
  }
}

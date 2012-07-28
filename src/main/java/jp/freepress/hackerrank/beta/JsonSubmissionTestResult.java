package jp.freepress.hackerrank.beta;

import java.util.Arrays;


public class JsonSubmissionTestResult {

  private JsonSubmissionGameData_Alt[] game;

  private String type;

  public JsonSubmissionGameData_Alt[] getGame() {
    return game;
  }

  public void setGame(JsonSubmissionGameData_Alt[] game) {
    this.game = game;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("SubmissionTestResult [game=");
    builder.append(Arrays.toString(game));
    builder.append(", type=");
    builder.append(type);
    builder.append("]");
    return builder.toString();
  }
}

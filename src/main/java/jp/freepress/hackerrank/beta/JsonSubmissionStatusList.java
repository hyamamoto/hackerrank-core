package jp.freepress.hackerrank.beta;

import java.util.Arrays;

// {"models":[{"challenge":"Tic tac toe","kind":"game","challenge_slug":"tic-tac-toe","id":XXXX,"language":"scala","created_at":"2012-07-26T08:52:43Z","time_ago":"less than a minute","status":"Queued"}],"total":1}

/**
 * A class for a list of submission statuses JSON text.
 */
public final class JsonSubmissionStatusList {

  private int total;

  private JsonSubmissionStatus[] models;

  public JsonSubmissionStatusList() {
    super();
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public JsonSubmissionStatus[] getModels() {
    return models;
  }

  public void setModels(JsonSubmissionStatus[] models) {
    this.models = models;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("SubmissionList [total=");
    builder.append(total);
    builder.append(", models=");
    builder.append(Arrays.toString(models));
    builder.append("]");
    return builder.toString();
  }

}

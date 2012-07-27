package jp.freepress.hackerrank.beta;

import java.util.Arrays;

// {"models":[{"challenge":"Tic tac toe","kind":"game","challenge_slug":"tic-tac-toe","id":177,"language":"scala","created_at":"2012-07-26T10:08:25Z","time_ago":"about 5 hours","status":"Queued"},{"challenge":"Tic tac toe","kind":"game","challenge_slug":"tic-tac-toe","id":176,"language":"scala","created_at":"2012-07-26T08:52:43Z","time_ago":"about 6 hours","status":"Queued"}],"total":2}

/**
 * A class for a list of submissions JSON text.
 */
public final class JsonSubmissionList {

  private int total;

  private JsonSubmission[] models;

  public JsonSubmissionList() {
    super();
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public JsonSubmission[] getModels() {
    return models;
  }

  public void setModels(JsonSubmission[] models) {
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

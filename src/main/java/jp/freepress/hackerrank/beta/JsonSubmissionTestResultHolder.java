package jp.freepress.hackerrank.beta;

// {"model":{"game":[{"player1":-1,"player2":-10,"player1_username":"User (You)","player2_username":"JudgeBot","game":[" 1"],"result":2,"message":"Incorrect Move Format. Your move was : . Please read the problem statement for correct move format"},{"player1":-10,"player2":-1,"player1_username":"JudgeBot","player2_username":"User (You)","game":["1 1 1"," 2"],"result":1,"message":"Incorrect Move Format. Your move was : . Please read the problem statement for correct move format"}],"type":"game"}}

/**
 * A result data class for your submitted code.
 */
public final class JsonSubmissionTestResultHolder {

  private JsonSubmissionTestResult model;

  public JsonSubmissionTestResultHolder() {
    super();
  }

  public JsonSubmissionTestResult getModel() {
    return model;
  }

  public void setModel(JsonSubmissionTestResult model) {
    this.model = model;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("SubmissionTestResultHolder [model=");
    builder.append(model);
    builder.append("]");
    return builder.toString();
  }
  
}
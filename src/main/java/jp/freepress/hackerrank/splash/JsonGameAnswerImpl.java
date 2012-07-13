package jp.freepress.hackerrank.splash;

public class JsonGameAnswerImpl implements JsonGameAnswer {

  private int exit = -1;
  private String message; // ex) "N should be in between 1 to 10000"

  @Override
  public int getExit() {
    return exit;
  }

  @Override
  public void setExit(int exit) {
    this.exit = exit;
  }

  @Override
  public String getMessage() {
    return message;
  }

  @Override
  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("GameAnswer [exit=");
    builder.append(exit);
    builder.append(", message=");
    builder.append(message);
    builder.append("]");
    return builder.toString();
  }

}

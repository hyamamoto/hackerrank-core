package jp.freepress.hackerrank.splash;

public class JsonChallengeImpl implements JsonChallenge {

  JsonChallengeGameImpl game;

  int exit; // 0: ok, 1: ng

  String message;

  @Override
  public JsonChallengeGameImpl getGame() {
    return game;
  }

  @Override
  public void setGame(JsonChallengeGameImpl game) {
    this.game = game;
  }

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
    builder.append("JsonChallenge [game=");
    builder.append(game);
    builder.append(", exit=");
    builder.append(exit);
    builder.append(", message=");
    builder.append(message);
    builder.append("]");
    return builder.toString();
  }

}

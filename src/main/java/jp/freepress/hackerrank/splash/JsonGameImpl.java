package jp.freepress.hackerrank.splash;

public class JsonGameImpl implements JsonGame {
  private JsonGameGameImpl game; // error: null

  private String ok;

  private int exit;// error: int 1

  private String message; // error: String 'You can't play that game!'

  private JsonGameImpl() {
    super();
  }

  @Override
  public JsonGameGameImpl getGame() {
    return game;
  }

  @Override
  public void setGame(JsonGameGameImpl game) {
    this.game = game;
  }

  @Override
  public String getOk() {
    return ok;
  }

  @Override
  public void setOk(String ok) {
    this.ok = ok;
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
    builder.append("Game [game=");
    builder.append(game);
    builder.append(", ok=");
    builder.append(ok);
    builder.append(", exit=");
    builder.append(exit);
    builder.append(", message=");
    builder.append(message);
    builder.append("]");
    return builder.toString();
  }

}

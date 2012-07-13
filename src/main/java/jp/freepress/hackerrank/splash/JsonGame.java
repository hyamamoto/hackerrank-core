package jp.freepress.hackerrank.splash;


public interface JsonGame {

  public JsonGameGameImpl getGame();

  public void setGame(JsonGameGameImpl game);

  public String getOk();

  public void setOk(String ok);

  public int getExit();

  public void setExit(int exit);

  public String getMessage();

  public void setMessage(String message);

}

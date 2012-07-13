package jp.freepress.hackerrank.splash;


public interface JsonChallenge {

  public JsonChallengeGameImpl getGame();

  public void setGame(JsonChallengeGameImpl game);

  public int getExit();

  public void setExit(int exit);

  public String getMessage();

  public void setMessage(String message);

  // Error:
  // game null,
  // exit 1
  // message String 'You can't play that game!'
  // This error maybe require relogin

}

package jp.freepress.hackerrank.beta;


public final class JsonSubmissionGameData {

  private String created_at;

  private String game;

  private int id;

  private String message;

  private int player1;

  private String player1_gravatar;

  private String player1_username;

  private int player2;

  private String player2_gravatar;

  private String player2_username;

  private int result;

  private String updated_at;

  public JsonSubmissionGameData() {
    super();
  }

  public String getCreated_at() {
    return created_at;
  }

  public void setCreated_at(String created_at) {
    this.created_at = created_at;
  }

  public String getGame() {
    return game;
  }

  public void setGame(String game) {
    this.game = game;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public int getPlayer1() {
    return player1;
  }

  public void setPlayer1(int player1) {
    this.player1 = player1;
  }

  public String getPlayer1_gravatar() {
    return player1_gravatar;
  }

  public void setPlayer1_gravatar(String player1_gravatar) {
    this.player1_gravatar = player1_gravatar;
  }

  public String getPlayer1_username() {
    return player1_username;
  }

  public void setPlayer1_username(String player1_username) {
    this.player1_username = player1_username;
  }

  public int getPlayer2() {
    return player2;
  }

  public void setPlayer2(int player2) {
    this.player2 = player2;
  }

  public String getPlayer2_gravatar() {
    return player2_gravatar;
  }

  public void setPlayer2_gravatar(String player2_gravatar) {
    this.player2_gravatar = player2_gravatar;
  }

  public String getPlayer2_username() {
    return player2_username;
  }

  public void setPlayer2_username(String player2_username) {
    this.player2_username = player2_username;
  }

  public int getResult() {
    return result;
  }

  public void setResult(int result) {
    this.result = result;
  }

  public String getUpdated_at() {
    return updated_at;
  }

  public void setUpdated_at(String updated_at) {
    this.updated_at = updated_at;
  }

}

// { "created_at" : "2012-07-26T15:53:20Z",
// "game" : "[\" 1\"]",
// "id" : XXXX,
// "message" :
// "\"Incorrect Move Format. Your move was : . Please read the problem statement for correct move format\"",
// "player1" : XXXX,
// "player1_gravatar" :
// "https://secure.gravatar.com/avatar/XXXX?d=https://ferrari.interviewstreet.com/challenges/noimage.png&s=25",
// "player1_username" : "XXXXXX",
// "player2" : XXXX,
// "player2_gravatar" :
// "https://secure.gravatar.com/avatar/XXXX?d=https://ferrari.interviewstreet.com/challenges/noimage.png&s=25",
// "player2_username" : "XXXXX",
// "result" : 2,
// "updated_at" : "2012-07-26T15:53:20Z"
// },

// "game" value could be
//"[\"0 0 1\", \"1 1 2\", \"1 0 1\", \"1 1 2\"]"
//"[\"0 0 1\", \"l 2\"]"
//"[\"0 0 1\", \"1 0 2\", \"1 1 1\", \"2 2 2\", \"1 1 1\"]"
//"[\"0 0 1\", \"1 1 2\", \"1 0 1\", \"1 1 2\"]"
//"[\"l 1\"]"
//["1 1 1", "2 0 2", "0 0 1", "2 2 2", "2 1 1", "0 1 2", "1 0 1", "1 2 2", "0 2 1"]
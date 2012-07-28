package jp.freepress.hackerrank.beta;

import java.util.Arrays;


public final class JsonSubmission {

  private int challenge_id;

  private String code;

  private int contest_id;

  private String created_at;

  private int game_lost;

  private int game_played;

  private int game_won;

  private JsonSubmissionGameData[] gamedata;

  private int hacker_id;

  private String kind;

  private String language;

  private int level;

  private String name;

  private int partial;

  private double score;

  private int solved;

  private int status;

  public JsonSubmission() {
    super();
  }
  
  public int getChallenge_id() {
    return challenge_id;
  }

  public void setChallenge_id(int challenge_id) {
    this.challenge_id = challenge_id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public int getContest_id() {
    return contest_id;
  }

  public void setContest_id(int contest_id) {
    this.contest_id = contest_id;
  }

  public String getCreated_at() {
    return created_at;
  }

  public void setCreated_at(String created_at) {
    this.created_at = created_at;
  }

  public int getGame_lost() {
    return game_lost;
  }

  public void setGame_lost(int game_lost) {
    this.game_lost = game_lost;
  }

  public int getGame_played() {
    return game_played;
  }

  public void setGame_played(int game_played) {
    this.game_played = game_played;
  }

  public int getGame_won() {
    return game_won;
  }

  public void setGame_won(int game_won) {
    this.game_won = game_won;
  }

  public JsonSubmissionGameData[] getGamedata() {
    return gamedata;
  }

  public void setGamedata(JsonSubmissionGameData[] gamedata) {
    this.gamedata = gamedata;
  }

  public int getHacker_id() {
    return hacker_id;
  }

  public void setHacker_id(int hacker_id) {
    this.hacker_id = hacker_id;
  }

  public String getKind() {
    return kind;
  }

  public void setKind(String kind) {
    this.kind = kind;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPartial() {
    return partial;
  }

  public void setPartial(int partial) {
    this.partial = partial;
  }

  public double getScore() {
    return score;
  }

  public void setScore(double score) {
    this.score = score;
  }

  public int getSolved() {
    return solved;
  }

  public void setSolved(int solved) {
    this.solved = solved;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("JsonSubmission [challenge_id=");
    builder.append(challenge_id);
    builder.append(", code.length=");
    builder.append( code != null ? code.length(): -1);
    builder.append(", contest_id=");
    builder.append(contest_id);
    builder.append(", created_at=");
    builder.append(created_at);
    builder.append(", game_lost=");
    builder.append(game_lost);
    builder.append(", game_played=");
    builder.append(game_played);
    builder.append(", game_won=");
    builder.append(game_won);
    builder.append(", gamedata=");
    builder.append(Arrays.toString(gamedata));
    builder.append(", hacker_id=");
    builder.append(hacker_id);
    builder.append(", kind=");
    builder.append(kind);
    builder.append(", language=");
    builder.append(language);
    builder.append(", level=");
    builder.append(level);
    builder.append(", name=");
    builder.append(name);
    builder.append(", partial=");
    builder.append(partial);
    builder.append(", score=");
    builder.append(score);
    builder.append(", solved=");
    builder.append(solved);
    builder.append(", status=");
    builder.append(status);
    builder.append("]");
    return builder.toString();
  }

}

// { "gamedata" : { "total" : 30 },
// "model" : { "challenge_id" : 1,
// "code" : "\nbrabrabrabrabrabrabra\n}",
// "contest_id" : 1,
// "created_at" : "2012-07-26T08:52:43Z",
// "game_lost" : 12,
// "game_played" : 30,
// "game_tied" : 1,
// "game_won" : 17,
// "gamedata" : [ { "created_at" : "2012-07-26T15:53:19Z",
// ...
// ...
// ...
// }
// ],
// "hacker_id" : XXXX,
// "id" : XXXX,
// "kind" : "game",
// "language" : "Javascript",
// "level" : 1,
// "name" : "Tic tac toe",
// "partial" : 0,
// "score" : 56.0,
// "solved" : 0,
// "status" : 1
// }
// }

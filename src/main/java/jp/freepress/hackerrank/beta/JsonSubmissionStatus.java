package jp.freepress.hackerrank.beta;

import org.apache.commons.lang3.StringUtils;

// {"challenge":"Tic tac toe","kind":"game","challenge_slug":"tic-tac-toe","id":XXXX,"language":"scala","created_at":"2012-07-26T10:08:25Z","time_ago":"about 5 hours","status":"Queued"},{"challenge":"Tic tac toe","kind":"game","challenge_slug":"tic-tac-toe","id":176,"language":"scala","created_at":"2012-07-26T08:52:43Z","time_ago":"about 6 hours","status":"Queued"}

/**
 * A class for a submission status JSON text.
 */
public final class JsonSubmissionStatus {

  private int id;

  private String challenge;

  private String kind;

  private String challenge_slug;

  private String language;

  private String created_at;

  private String time_ago;

  private String status;

  public JsonSubmissionStatus() {
    super();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getChallenge() {
    return challenge;
  }

  public void setChallenge(String challenge) {
    this.challenge = challenge;
  }

  public String getKind() {
    return kind;
  }

  public void setKind(String kind) {
    this.kind = kind;
  }

  public String getChallenge_slug() {
    return challenge_slug;
  }

  public void setChallenge_slug(String challenge_slug) {
    this.challenge_slug = challenge_slug;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getCreated_at() {
    return created_at;
  }

  public void setCreated_at(String created_at) {
    this.created_at = created_at;
  }

  public String getTime_ago() {
    return time_ago;
  }

  public void setTime_ago(String time_ago) {
    this.time_ago = time_ago;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Submission [id=");
    builder.append(id);
    builder.append(", challenge=");
    builder.append(challenge);
    builder.append(", kind=");
    builder.append(kind);
    builder.append(", challenge_slug=");
    builder.append(challenge_slug);
    builder.append(", language=");
    builder.append(language);
    builder.append(", created_at=");
    builder.append(created_at);
    builder.append(", time_ago=");
    builder.append(time_ago);
    builder.append(", status=");
    builder.append(status);
    builder.append("]");
    return builder.toString();
  }

  public static class Util {
    public static String toSimpleLineString(JsonSubmissionStatus stat) {
      StringBuilder builder = new StringBuilder();
      builder.append("");
      builder.append(StringUtils.rightPad(Integer.toString(stat.id), 4));
      builder.append(",");
      builder.append(StringUtils.leftPad(stat.challenge, 12));
      builder.append(",");
      builder.append(StringUtils.leftPad(stat.language, 8));
      builder.append(",");
      builder.append(StringUtils.leftPad(stat.time_ago, 16));
      builder.append(",");
      builder.append(StringUtils.leftPad(stat.status, 10));
      builder.append(", ");
      builder.append(stat.created_at);
      return builder.toString();
    }
  }

}

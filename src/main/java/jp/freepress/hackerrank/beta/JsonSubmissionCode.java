package jp.freepress.hackerrank.beta;

/**
 * A data class for your code submission.
 * 
 * 
 * awwwww. this is experimental
 */
public final class JsonSubmissionCode {

  private Integer id = null;
  
  private String code;

  private String language;

  private int challenge_id;

  private int contest_id;

  private int level;

  public JsonSubmissionCode() {
    super();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public int getChallenge_id() {
    return challenge_id;
  }

  public void setChallenge_id(int challenge_id) {
    this.challenge_id = challenge_id;
  }

  public int getContest_id() {
    return contest_id;
  }

  public void setContest_id(int contest_id) {
    this.contest_id = contest_id;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }
  
}
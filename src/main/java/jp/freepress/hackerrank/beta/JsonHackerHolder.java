package jp.freepress.hackerrank.beta;

// {"model":{"username":"XXXX","gravatar":"https://secure.gravatar.com/avatar/XXXX?d=https://ferrari.interviewstreet.com/challenges/noimage.png&s=25"}}

/**
 * A class for a wrapper of a hacker JSON text.
 */
public final class JsonHackerHolder {

  private JsonHacker model;

  public JsonHackerHolder() {
    super();
  }

  public JsonHacker getModel() {
    return model;
  }

  public void setModel(JsonHacker model) {
    this.model = model;
  }

}

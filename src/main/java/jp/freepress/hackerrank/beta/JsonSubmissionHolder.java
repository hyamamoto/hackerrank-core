package jp.freepress.hackerrank.beta;


/**
 * A class for a list of submissions JSON text.
 */
public final class JsonSubmissionHolder {

  // I think this will be fixed in the future
  private JsonSubmissionGameDataTotal gamedata;

  private JsonSubmission model;

  public JsonSubmissionHolder() {
    super();
  }

  public JsonSubmissionGameDataTotal getGamedata() {
    return gamedata;
  }

  public void setGamedata(JsonSubmissionGameDataTotal gamedata) {
    this.gamedata = gamedata;
  }

  public JsonSubmission getModel() {
    return model;
  }

  public void setModel(JsonSubmission model) {
    this.model = model;
  }

  @Override
  public String toString() {
    return "SubmissionHolder [gamedata=" + gamedata + ", model=" + model + "]";
  }

}

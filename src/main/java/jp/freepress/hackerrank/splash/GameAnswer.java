package jp.freepress.hackerrank.splash;

import jp.freepress.hackerrank.core.Answer;

/**
 * 
 * @see GameProblem
 * @see GameSolver
 */
public class GameAnswer implements Answer {

  private boolean done;

  private String text;

  private int number;

  public GameAnswer() {
    super();
  }

  /**
   * @param done <tt>true</tt> if the answer is complete and can be sent back to the server.
   * @param text a text value to send back to the server (<tt>done && number != -1</tt>) or a human
   *        readable debug message (<tt>!done</tt>).
   * @param number a number value to send back to the server (<tt>done</tt>). <tt>-1</tt> means
   *        "do not use this number value, use text.".
   */
  public GameAnswer(boolean done, String text, int number) {
    super();
    this.done = done;
    this.text = text;
    this.number = number;
  }

  public boolean isDone() {
    return done;
  }

  public void setDone(boolean done) {
    this.done = done;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("GameAnswer [done=");
    builder.append(done);
    builder.append(", text=\"");
    builder.append(text);
    builder.append("\", number=");
    builder.append(number);
    builder.append("]");
    return builder.toString();
  }
}
